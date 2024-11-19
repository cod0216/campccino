package com.ssafy.enjoyCamp.mapper;

import com.ssafy.enjoyCamp.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void registerUser(UserDTO user);
    UserDTO findByUsername(String userId);
}