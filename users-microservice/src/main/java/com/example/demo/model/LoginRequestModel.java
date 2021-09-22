package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestModel {
    @NotNull(message = "email could not be null")
    @Email
    private String email;
    @Size(min = 8,max = 16,message = "the password must contain at least height characters")
    private String password;
}
