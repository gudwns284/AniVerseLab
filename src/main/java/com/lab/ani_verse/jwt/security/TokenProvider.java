package com.lab.ani_verse.jwt.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenProvider {
	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.access-expiration}")
	private long accessTokenExpirationTime;

	@Value("${jwt.refresh-expiration}")
	private long refreshTokenExpirationTime;

	
	// https://javadoc.io/doc/io.jsonwebtoken/jjwt-api/0.12.2/deprecated-list.html
	// 0.12.0 부터
	// setSigningKey -> verifyWith()
	// parseClaimsJws -> parseUnsecuredClaims()
	// getBody -> getPayload()
	// 로 변경
	
	
//	private Key getSigningKey() {
//		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
//
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
	// 키 생성
	private SecretKey getSigningKey() {
		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// Access Token 생성
	public String createAccessToken(String riderId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + accessTokenExpirationTime);
		
//		return Jwts.builder()
//				.setSubject(riderId)
//				.setIssuedAt(now)
//				.setExpiration(expiryDate)
//				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
//				.compact();
		return Jwts.builder()
				.subject(riderId)
				.issuedAt(now)
				.expiration(expiryDate)
				.signWith(getSigningKey(), Jwts.SIG.HS256)
				.compact();
		
	}

	// https://velog.io/@mrcocoball2/JJWT-JJWT-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EB%B2%84%EC%A0%84%EC%97%90-%EB%94%B0%EB%A5%B8-%EB%B3%80%EA%B2%BD-%EC%82%AC%ED%95%AD
	// Refresh Token 생성
	public String createRefreshToken(String riderId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + accessTokenExpirationTime);
		System.out.println(expiryDate);
//		return Jwts.builder()
//				.setSubject(riderId)
//				.setIssuedAt(now)
//				.setExpiration(expiryDate)
//				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
		return Jwts.builder()
				.subject(riderId)
				.issuedAt(now)
				.expiration(expiryDate)
				.signWith(getSigningKey(), Jwts.SIG.HS256)
				.compact();
	}
    // 토큰에서 라이더 ID 추출 (Access, Refresh 동일하게 사용)
    public String getRiderIdFromToken(String token) {
//        return Jwts.builder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();

        return Jwts.parser()
        		.verifyWith(getSigningKey())
        		.build()
        		.parseUnsecuredClaims(token)
        		.getPayload()
                .getSubject();
        
    }
    
    // 토큰 유효성 검증 (Access, Refresh 동일하게 사용)
	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith(getSigningKey()).build().parseUnsecuredClaims(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}
}
