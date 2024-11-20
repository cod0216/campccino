package com.ssafy.campcino.model;
import lombok.Data;

import java.sql.*;

@Data
public class UserDto {
    private String userId;
    private String userPassword;
    private String userPhone;
    private int userAge;
    private String userEmail;
    private String userGender;
    private Timestamp userSignup;
    private Timestamp userDelete;
    private long userMileage;
    private String userUrl;
    private String userTempAuth;
    private String userRes;
    private String userGrade;
}
