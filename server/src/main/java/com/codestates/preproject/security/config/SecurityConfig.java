package com.codestates.preproject.security.config;

import com.codestates.preproject.security.exception.CustomAuthenticationEntryPoint;
import com.codestates.preproject.security.filter.JwtAuthenticationFilter;
import com.codestates.preproject.security.jwt.JwtProvider;
import com.codestates.preproject.security.jwt.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final MemberDetailsService memberDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*/members", "/*/members/login", "/docs/*").permitAll()
                .antMatchers(HttpMethod.GET, "/*/questions/**", "/*/questions", "/*/answers/**", "/*/comments/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider, memberDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
