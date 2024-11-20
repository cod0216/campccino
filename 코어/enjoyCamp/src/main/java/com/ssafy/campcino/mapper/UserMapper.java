package com.ssafy.campcino.repository;

import com.ssafy.campcino.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void registerUser(UserDto user);
    UserDto findByUsername(String userId);
}