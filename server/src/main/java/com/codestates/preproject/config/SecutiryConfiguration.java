package com.codestates.preproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.Security;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecutiryConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers()
                .frameOptions().sameOrigin() // 개발 환경에서 H2 콘솔을 사용할 수 있도록 추가한 것
                .and()
                .csrf().disable() // CSRF 공격 비활성화 로컬환경에서 필요하지 않음. disable 처리 안할 시 403 에러 발생
                .cors(withDefaults()) // cors(withDefaults)일 경우, corsConfigurationSource Bean을 제공하여 CorsFilters 적용
                .formLogin().disable() // CSR 방식 사용으로 form 로그인 비활성화
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() //TODO : JWT 적용 이후 HTTP request 접근 비활성화 시킬 것
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 모든 출처에 대해 HTTP 통신을 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE")); //파라미터로 지정한 HTTP Method에 대한 HTTP 통신을 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 URL 앞에서 구성한 CORS 정책을 적용
        return source;
    }
}
