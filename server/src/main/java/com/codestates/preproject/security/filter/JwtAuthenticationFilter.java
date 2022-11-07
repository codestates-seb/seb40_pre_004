package com.codestates.preproject.security.filter;

import com.codestates.preproject.security.jwt.JwtProvider;
import com.codestates.preproject.security.jwt.MemberDetailsService;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberDetailsService memberDetailsService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, MemberDetailsService memberDetailsService) {
        this.jwtProvider = jwtProvider;
        this.memberDetailsService = memberDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String access = request.getHeader("Authorization");
        String refresh = request.getHeader("Refresh");
        String requestURI = request.getRequestURI();

        if ((!Objects.isNull(access) && access.startsWith("Bearer ")) || requestURI.equals("/members/reissue")) {
            try {

                if (requestURI.equals("/members/reissue")) {
                    String rtkSubject = jwtProvider.getSubject(refresh);
                    UserDetails userDetails = memberDetailsService.loadUserByUsername(rtkSubject);
                    Authentication token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
                else {
                    String atk = access.substring(7);
                    String atkSubject = jwtProvider.getSubject(atk);

                    if (jwtProvider.isBlackList(atk)) {
                        throw new JwtException("유효하지 않은 AccessToken 입니다.");
                    }

                    UserDetails userDetails = memberDetailsService.loadUserByUsername(atkSubject);
                    Authentication token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            } catch (JwtException e) {
                request.setAttribute("exception", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}

