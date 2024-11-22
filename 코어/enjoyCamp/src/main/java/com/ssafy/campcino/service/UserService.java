package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.requsetDto.JoinDto;
import com.ssafy.campcino.model.UserEntity;

public interface UserService {
    void registerUser(JoinDto joinDto); // 회원가입
    UserEntity findByUserId(String userId); // 아이디로 사용자 조회
    void updateRefreshToken(String userId, String refreshToken); // Refresh Token 갱신
}
