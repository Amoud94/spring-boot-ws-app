package com.example.demo.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private String userID;
    private String encryptedPassword;

}
