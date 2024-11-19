package com.ssafy.enjoyCamp.dto;
import lombok.Data;

import java.sql.*;

@Data
public class UserDTO {
    private String userId;
    private String password;
    private String email;


}
