package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.requestDto.JoinDto;
import com.ssafy.campcino.mapper.UserMapper;
import com.ssafy.campcino.model.UserEntity;
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
    public UserEntity findByUserId(String userId) {
        UserEntity temp = userMapper.findByUserId(userId);
        System.out.println("temp = " + temp);
        return temp;
    }

    @Override
    public void updateRefreshToken(String userId, String refreshToken) {
        userMapper.updateRefreshToken(userId, refreshToken);
    }

}
