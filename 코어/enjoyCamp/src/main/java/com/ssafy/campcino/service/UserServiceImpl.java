package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.requsetDto.JoinDto;
import com.ssafy.campcino.mapper.UserMapper;
import com.ssafy.campcino.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void registerUser(JoinDto joinDto) {
        userMapper.insertUser(joinDto);
    }

    @Override
    public UserDto findByUserId(String userId) {
        UserDto temp = userMapper.findByUserId(userId);
        System.out.println("temp = " + temp);
        return temp;
    }

    @Override
    public void updateRefreshToken(String userId, String refreshToken) {
        userMapper.updateRefreshToken(userId, refreshToken);
    }

}
