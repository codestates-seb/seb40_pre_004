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
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisDao redisDao;
    private final ObjectMapper objectMapper;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expiration-minutes}")
    private int accessTokenExpirationMinutes;

    @Value("${jwt.refresh-token-expiration-minutes}")
    private int refreshTokenExpirationMinutes;

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return key;
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
        String atk = createToken(atkSubject, accessTokenExpirationMinutes);
        String rtk = createToken(rtkSubject, refreshTokenExpirationMinutes);
        redisDao.setValues(memberResponseDto.getEmail(), rtk, Duration.ofMinutes((long) refreshTokenExpirationMinutes));
        return new TokenResponseDto(atk, rtk);
    }

    private String createToken(Subject subject, int tokenLive) throws JsonProcessingException {
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subjectStr);

        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(getTokenExpiration(tokenLive))
                .signWith(key)
                .compact();
    }

    public Subject getSubject(String token) throws JsonProcessingException {
        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        String subjectStr = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }

    public TokenResponseDto reissueAtk(MemberResponseDto memberResponseDto) throws JsonProcessingException {
        String rtkInRedis = redisDao.getValues(memberResponseDto.getEmail());
        if (Objects.isNull(rtkInRedis)) {
            throw new JwtException("인증 정보가 만료되었습니다.");
        }

        Subject atkSubject = Subject.atk(
                memberResponseDto.getMemberId(),
                memberResponseDto.getEmail(),
                memberResponseDto.getDisplayName());
        String atk = createToken(atkSubject, accessTokenExpirationMinutes);
        return new TokenResponseDto(atk, null);
    }
}
