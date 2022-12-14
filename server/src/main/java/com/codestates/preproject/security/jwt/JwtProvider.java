package com.codestates.preproject.security.jwt;

import com.codestates.preproject.domain.member.dto.MemberResponseDto;
import com.codestates.preproject.domain.member.entity.Member;
import com.codestates.preproject.security.dto.TokenResponseDto;
import com.codestates.preproject.security.redis.RedisDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisDao redisDao;

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

        String atk = delegateAccessToken(memberResponseDto);
        String rtk = delegateRefreshToken(memberResponseDto);
        redisDao.setValues(memberResponseDto.getEmail(), rtk, Duration.ofMinutes((long) refreshTokenExpirationMinutes));
        return new TokenResponseDto(atk, rtk);
    }


    private String delegateAccessToken(MemberResponseDto memberResponseDto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("displayName", memberResponseDto.getDisplayName());
        claims.put("memberId", memberResponseDto.getMemberId());

        String subject = memberResponseDto.getEmail();
        Date expiration = getTokenExpiration(accessTokenExpirationMinutes);
        String base64EncodedSecretKey = encodeBase64SecretKey(secretKey);

        return generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
    }

    private String delegateRefreshToken(MemberResponseDto memberResponseDto) {
        String subject = memberResponseDto.getEmail();
        Date expiration = getTokenExpiration(refreshTokenExpirationMinutes);
        String base64EncodedSecretKey = encodeBase64SecretKey(secretKey);

        return generateRefreshToken(subject, expiration, base64EncodedSecretKey);
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(String subject,
                                       Date expiration,
                                       String base64EncodedSecretKey) {

        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public String getSubject(String jws) {
        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody()
                .getSubject();

    }

    public Jws<Claims> getClaims(String jws) {
        Key key = getKeyFromBase64EncodedKey(encodeBase64SecretKey(secretKey));

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);

        return claims;
    }

    public TokenResponseDto reissueAtk(MemberResponseDto memberResponseDto) throws JwtException {
        String rtkInRedis = redisDao.getValues(memberResponseDto.getEmail());
        if (Objects.isNull(rtkInRedis)) {
            throw new JwtException("?????? ????????? ?????????????????????.");
        }

        String atk = delegateAccessToken(memberResponseDto);
        return new TokenResponseDto(atk, null);
    }

    public void deleteRtk(MemberResponseDto memberResponseDto) throws JwtException {
        redisDao.deleteValues(memberResponseDto.getEmail());
    }

    public void setBlackListAtk(String bearerAtk) {
        String atk = bearerAtk.substring(7);
        long expiration = getClaims(atk).getBody().getExpiration().getTime();
        long now = Calendar.getInstance().getTime().getTime();

        redisDao.setValues(atk, "logout", Duration.ofMillis(expiration-now));
    }

    public boolean isBlackList(String atk) {
        return StringUtils.hasText(redisDao.getValues(atk));
    }
}
