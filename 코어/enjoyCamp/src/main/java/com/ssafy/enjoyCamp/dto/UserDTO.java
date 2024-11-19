package com.ssafy.enjoyCamp.dto;
import lombok.Data;

import java.sql.*;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String email;


}
