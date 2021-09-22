package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
    @NotNull(message = "firstname could not be null")
    @Size(min = 2,message = "firstname should not be less then two characters")
    private String firstname;
    @NotNull(message = "lastname could not be null")
    @Size(min = 2,message = "lastname should not be less then two characters")
    private String lastname;
    @NotNull(message = "email could not be null")
    @Email
    private String email;
    @Size(min = 8,max = 16,message = "the password must contain at least height characters")
    private String password;
}
