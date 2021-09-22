package com.example.demo.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 50)
    private String firstname;
    @Column(nullable = false,length = 50)
    private String lastname;
    @Column(nullable = false,length = 250,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String encryptedPassword;
    @Column(nullable = false,unique = true)
    private String userID;
}
