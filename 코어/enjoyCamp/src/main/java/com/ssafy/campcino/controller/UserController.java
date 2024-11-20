//package com.ssafy.campcino.controller;
//
//
//import com.ssafy.enjoyCamp.service.UserService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @PostMapping("/join")
//    public ResponseEntity<String> registerUser(@RequestBody UserDto user){
//
//        System.out.println("user = " + user);
//        userService.registerUser(user);
//        return ResponseEntity.ok("유저 정보 등록 성공적으로 완료");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody UserDTO user){
//        UserDTO foundUser = userService.findByUsername(user.getUserId());
//        if (foundUser != null && new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword())) {
//            // JWT 생성 및 반환
//            String token = "Generated JWT"; // 여기에 JWT 생성 로직 추가
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//}