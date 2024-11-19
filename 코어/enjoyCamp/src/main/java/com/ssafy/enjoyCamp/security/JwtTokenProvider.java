package com.ssafy.enjoyCamp.security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey = "your-secret-key"; // 실제로는 안전한 곳에 보관해야 해요.
    private long validityInMilliseconds = 3600000; // 토큰의 유효기간: 1시간

    // 토큰 생성 메서드
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username); // 토큰에 담을 정보 설정

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); // 토큰 만료 시간

        return Jwts.builder()
                .setClaims(claims) // 정보 설정
                .setIssuedAt(now) // 토큰 발행 시간
                .setExpiration(validity) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 시크릿 키 설정
                .compact();
    }

    // 토큰에서 유저 정보 추출 메서드
    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey) // 시크릿 키 설정
                .parseClaimsJws(token) // 토큰 파싱
                .getBody()
                .getSubject();
    }

    // 토큰 유효성 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token); // 토큰 파싱 및 검증
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 유효하지 않은 토큰인 경우
        }
    }

    // Request의 Header에서 토큰 정보를 가져오는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); // 헤더에서 토큰 값 가져오기
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 부분 제거
        }
        return null;
    }
}
