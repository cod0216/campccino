package com.ssafy.campcino.mapper;

import com.ssafy.campcino.dto.requestDto.JoinDto;
import com.ssafy.campcino.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(JoinDto joinDto); // 회원정보 삽입
    UserEntity findByUserId(String userId); // 아이디로 사용자 조회
    void updateRefreshToken(String userId, String refreshToken); // Refresh Token 업데이트
}
