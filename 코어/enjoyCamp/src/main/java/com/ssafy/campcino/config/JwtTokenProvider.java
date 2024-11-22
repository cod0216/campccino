
package com.ssafy.campcino.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private Key accessKey;
    private Key refreshKey;
    private final UserDetailsService userDetailsService;
    private static final String SECRET_KEY = "my-secret-key-for-access-token-which-should-be-very-secure";
    private static final String REFRESH_SECRET_KEY = "my-secret-key-for-refresh-token-which-should-be-different";

    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 30; // 30 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 days


    private final long expirationTime = 120000; // 120초

    //15분~1시간  리프레쉬는 1주일 정도

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        // application.properties에서 키를 로드
        accessKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        refreshKey = Keys.hmacShaKeyFor(REFRESH_SECRET_KEY.getBytes());
    }


    //, List<String> roles
    public String generateAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(accessKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(refreshKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token, boolean isRefreshToken) {
        try {
            Key key = isRefreshToken ? refreshKey : accessKey;
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }// 토큰(유효시간)으로 만들 수 있는 놈이면 true, 아니면 false

    public String getUserIdFromToken(String token, boolean isRefreshToken) {
        Key key = isRefreshToken ? refreshKey : accessKey;
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

//    public Authentication getAuthentication(String token, boolean isRefreshToken) {
//        String username = getUserIdFromToken(token);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
}


/*
Todo


제너레이트 토큰-> 리프레쉬 토큰, 토큰 엑세스 토큰
클레임
 */

//package com.ssafy.campcino.service;
//
//import io.jsonwebtoken.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private String secretKey = "trump"; // 실제로는 안전한 곳에 보관해야 해요.
//    private long validityInMilliseconds = 3600000; // 토큰의 유효기간: 1시간
//
//    // 토큰 생성 메서드
//    public String createToken(String username) {
//        Claims claims = Jwts.claims().setSubject(username); // 토큰에 담을 정보 설정
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds); // 토큰 만료 시간
//
//        return Jwts.builder()
//                .setClaims(claims) // 정보 설정
//                .setIssuedAt(now) // 토큰 발행 시간
//                .setExpiration(validity) // 토큰 만료 시간 설정
//                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 시크릿 키 설정
//                .compact();
//    }
//
//    // 토큰에서 유저 정보 추출 메서드
//    public String getUsername(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey) // 시크릿 키 설정
//                .parseClaimsJws(token) // 토큰 파싱
//                .getBody()
//                .getSubject();
//    }
//
//    // 토큰 유효성 검증 메서드
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token); // 토큰 파싱 및 검증
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false; // 유효하지 않은 토큰인 경우
//        }
//    }
//
//    // Request의 Header에서 토큰 정보를 가져오는 메서드
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization"); // 헤더에서 토큰 값 가져오기
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7); // "Bearer " 부분 제거
//        }
//        return null;
//    }
//}