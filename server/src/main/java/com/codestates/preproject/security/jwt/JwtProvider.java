package com.codestates.preproject.security.jwt;

import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import com.codestates.preproject.security.dto.TokenResponseDto;
import com.codestates.preproject.security.redis.RedisDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisDao redisDao;
    private final ObjectMapper objectMapper;

    @Value("${jwt.secret-key}")
    private String key;

    @Value("${jwt.access-token-expiration-minutes}")
    private Long atkLive;

    @Value("${jwt.refresh-token-expiration-minutes}")
    private Long rtkLive;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponseDto createTokensByLogin(MemberResponseDto memberResponseDto) throws JsonProcessingException {
        Subject atkSubject = Subject.atk(
                memberResponseDto.getMemberId(),
                memberResponseDto.getEmail(),
                memberResponseDto.getDisplayName());
        Subject rtkSubject = Subject.rtk(
                memberResponseDto.getMemberId(),
                memberResponseDto.getEmail(),
                memberResponseDto.getDisplayName());
        String atk = createToken(atkSubject, atkLive);
        String rtk = createToken(rtkSubject, rtkLive);
        redisDao.setValues(memberResponseDto.getEmail(), rtk, Duration.ofMillis(rtkLive));
        return new TokenResponseDto(atk, rtk);
    }

    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subjectStr);
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenLive))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Subject getSubject(String atk) throws JsonProcessingException {
        String subjectStr = Jwts.parser().setSigningKey(key).parseClaimsJws(atk).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }

    public TokenResponseDto reissueAtk(MemberResponseDto memberResponseDto) throws JsonProcessingException {
        String rtkInRedis = redisDao.getValues(memberResponseDto.getEmail());
        if (Objects.isNull(rtkInRedis)) throw new JwtException("인증 정보가 만료되었습니다.");
        Subject atkSubject = Subject.atk(
                memberResponseDto.getMemberId(),
                memberResponseDto.getEmail(),
                memberResponseDto.getDisplayName());
        String atk = createToken(atkSubject, atkLive);
        return new TokenResponseDto(atk, null);
    }
}
