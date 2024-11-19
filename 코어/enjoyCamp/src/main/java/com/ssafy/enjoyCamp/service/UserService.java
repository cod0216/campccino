package com.ssafy.enjoyCamp.service;

import com.ssafy.enjoyCamp.dto.UserDTO;
import com.ssafy.enjoyCamp.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        userMapper.registerUser(user);
    }

    public UserDTO findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
