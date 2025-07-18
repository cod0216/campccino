package com.campccino.service;


import com.campccino.dto.requestDto.JoinDto;
import com.campccino.dto.requestDto.LoginDto;
import com.campccino.dto.requestDto.UpdateUserDto;
import com.campccino.model.UserEntity;

public interface UserService {
    void registerUser(JoinDto joinDto); // 회원가입
    UserEntity findByUserId(String userId); // 아이디로 사용자 조회
    void updateRefreshToken(String userId, String refreshToken); // Refresh Token 갱신
    boolean validateRefreshToken(String userId, String refreshToken); // Refresh Token 유효성 검증
    void updateUserInfo(String userId, UpdateUserDto updateUserDto);
    void deleteRefreshToken(String userId); // Refresh Token 삭제 (로그아웃 시)

    String authenticateUser(LoginDto loginDto);


}
