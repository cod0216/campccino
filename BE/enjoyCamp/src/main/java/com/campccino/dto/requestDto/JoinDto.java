package com.campccino.dto.requestDto;

import lombok.Data;

@Data
public class JoinDto {
    private String userId;
    private String userPassword;
    private String userPhone;
    private int userAge;
    private String userEmail;
    private String userGender;

}
