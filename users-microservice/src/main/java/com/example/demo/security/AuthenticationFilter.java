package com.example.demo.security;

import com.example.demo.model.LoginRequestModel;
import com.example.demo.service.UserService;
import com.example.demo.shared.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Environment environment;
    UserService userService;

    @Autowired
    public AuthenticationFilter(Environment environment, UserService userService,
                                AuthenticationManager authenticationManager) {
        this.environment = environment;
        this.userService = userService;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try{
            LoginRequestModel creds = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestModel.class);

            UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()
            );

            return this.getAuthenticationManager().authenticate(token);

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        UserDTO userDetails = userService.getUserDetailsByEmail(username);

        String token = Jwts.builder()
                .setSubject(userDetails.getUserID())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512,environment.getProperty("token.secret"))
                .compact();
        response.addHeader("Authorization",token);
        response.addHeader("userID",userDetails.getUserID());


    }
}
