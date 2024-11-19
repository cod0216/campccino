package com.ssafy.enjoyCamp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;


import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); // 토큰 추출
        if (token != null && jwtTokenProvider.validateToken(token)) { // 토큰 검증
            String username = jwtTokenProvider.getUsername(token); // 토큰에서 사용자 정보 추출
            // 필요한 경우 Authentication 객체를 만들어 SecurityContext에 설정 가능
        }
        chain.doFilter(request, response); // 다음 필터로 이동
    }
}
