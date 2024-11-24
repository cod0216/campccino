package com.ssafy.campcino.controller;

import com.ssafy.campcino.config.JwtTokenProvider;
import com.ssafy.campcino.dto.requestDto.JoinDto;
import com.ssafy.campcino.dto.requestDto.LoginDto;
import com.ssafy.campcino.dto.responseDto.LoginResponseDto;
import com.ssafy.campcino.model.UserEntity;
import com.ssafy.campcino.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<String> registerUser(@RequestBody JoinDto joinDto) {
        System.out.println("joinDto = " + joinDto);

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(joinDto.getUserPassword());
        joinDto.setUserPassword(encryptedPassword);

        // DB에 저장
        userService.registerUser(joinDto);

        // 성공 메시지 반환
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        String username = userService.authenticateUser(loginDto);
        String accessToken = jwtTokenProvider.generateAccessToken(username);
        String refreshToken = jwtTokenProvider.generateRefreshToken(username);

        // Set HttpOnly cookies
        Cookie accessCookie = new Cookie("access_token", accessToken);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true); // Use HTTPS
        accessCookie.setPath("/");
        accessCookie.setMaxAge(120); // 120 seconds

        Cookie refreshCookie = new Cookie("refresh_token", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true); // Use HTTPS
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(604800); // 7 days

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refresh_token".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
            String username = jwtTokenProvider.getUserIdFromToken(refreshToken);
            String newAccessToken = jwtTokenProvider.generateAccessToken(username);

            // Set new access token as cookie
            Cookie accessCookie = new Cookie("access_token", newAccessToken);
            accessCookie.setHttpOnly(true);
            accessCookie.setSecure(true);
            accessCookie.setPath("/");
            accessCookie.setMaxAge(120);

            response.addCookie(accessCookie);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // Clear cookies
        Cookie accessCookie = new Cookie("access_token", null);
        accessCookie.setHttpOnly(true);
        accessCookie.setSecure(true);
        accessCookie.setPath("/");
        accessCookie.setMaxAge(0);

        Cookie refreshCookie = new Cookie("refresh_token", null);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(0);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return ResponseEntity.ok().build();
    }




//    @PostMapping("/join")
//    public ResponseEntity<String> registerUser(@ModelAttribute JoinDto join){
//        System.out.println("user = " + join);
//
//        String accessToken= jwtTokenProvider.generateAccessToken(join.getUserEmail());
//        String refreshToken= jwtTokenProvider.generateRefreshToken(join.getUserEmail());
//
//
//        // 로그인 할 때
//        //리프레쉬는 DB랑 클라이언트 측에 저장
//        //어세스 토큰은 클라이언트 측에만 저장
//        /*
//
//        클라이언트에서 요청할때 헤더에 어세스 토큰을 넣어서 요청
//        어세스 토큰이 지속시간이 짧으니까 만약에 어세스 토큰이 만료가 됏으면 JWT 토큰에 유효성검사 하는게 ㅇㅆ는데 이게 에세스토큰 넣어서 만료 되는지 아닌지 리턴 가능
//
//        만약 만료 됐으면 클라이언트에 알리고
//        클라이언트에 리프래쉬 토큰 있으니까 클라이언트가 헤더에 리프레쉬 토큰 넣어서 다시 보낸다.
//        서바는 클라이언트 측에서 온 리프레쉬 토큰이랑 파싱해서 나온 id나 네임은 DB에서 조회하고 거기의 엤는 리프레쉬 토큰을 가져오고 이 둘이 똑같으면
//        다시 에셉스 토큰을 발급해서 클라이언트에 다시 보내준다.
//
//        클라이언트는 어셉트 토큰을 다시 저장하고 빠꾸먹었던 요청을 다시 새로발급 받은 토큰을 해더에 넣어서 다시 서버에 요청한다.
//
//        리프래쉬 토큰도 유효성 검사(만료) 검사는 해야된다 만료가 안되면 위 사이클 반복 아니면 다시 로그인 해라
//
//        리프래쉬 토큰도 탈취 당할 수 있어서 어쉡스 토큰을 다시 발행할때 리프래쉬 토큰도 같이 발행하는 경우도 있다. -> 이게 보안의로 뛰어남
//         */
//        userService.registerUser(join);
//        return ResponseEntity.ok("유저 정보 등록 성공적으로 완료");
//
}