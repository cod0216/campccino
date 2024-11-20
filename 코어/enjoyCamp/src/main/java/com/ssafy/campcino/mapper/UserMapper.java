package com.ssafy.campcino.mapper;

import com.ssafy.campcino.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void registerUser(UserEntity user);
    UserEntity findByUsername(String userId);
}