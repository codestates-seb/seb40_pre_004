package com.codestates.preproject.config;

import com.codestates.preproject.auth.filter.JwtAuthenticationFilter;
import com.codestates.preproject.auth.filter.JwtVerificationFilter;
import com.codestates.preproject.auth.handler.MemberAccessDeniedHandler;
import com.codestates.preproject.auth.handler.MemberAuthenticationEntryPoint;
import com.codestates.preproject.auth.handler.MemberAuthenticationFailureHandler;
import com.codestates.preproject.auth.handler.MemberAuthenticationSuccessHandler;
import com.codestates.preproject.auth.jwt.JwtTokenizer;
import com.codestates.preproject.auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtills;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtills ) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtills = authorityUtills;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers()
                .frameOptions().sameOrigin() // 개발 환경에서 H2 콘솔을 사용할 수 있도록 추가한 것
                .and()

                .csrf().disable() //TODO : CSRF 공격 비활성화 로컬환경에서 필요하지 않음. disable 처리 안할 시 403 에러 발생
                .cors(withDefaults()) // cors(withDefaults)일 경우, corsConfigurationSource Bean을 제공하여 CorsFilters 적용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .formLogin().disable() // CSR 방식 사용으로 form 로그인 비활성화
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())

                .and()
                .apply(new CustomFilterConfigurer())

                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/*/members").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("ADMIN", "USER")
                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "/*/questions").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "*/answers").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "*/comments").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .cors();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        //configuration.setAllowedOrigins(Arrays.asList("http://seb40-pre-004-stack-overflow.s3-website.ap-northeast-2.amazonaws.com:80", "http://localhost:3000/" )); // 모든 출처에 대해 HTTP 통신을 허용
        configuration.setAllowedOrigins(Arrays.asList("http://ec2-43-201-141-158.ap-northeast-2.compute.amazonaws.com:8080", "http://localhost:3000/" )); // 모든 출처에 대해 HTTP 통신을 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS")); //파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 URL 앞에서 구성한 CORS 정책을 적용
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtills);

            builder.
                    addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
