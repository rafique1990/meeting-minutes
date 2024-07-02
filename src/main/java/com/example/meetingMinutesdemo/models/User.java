package com.example.meetingMinutesdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;


@Data
@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String   first_name   ;
    private String last_name   ;
    private String  username ;
    private String   password ;
    private Date dob  ;
    private Boolean enabled ;
    @Column(name = "full_name")
    private String name;
    @Email
    private String email;
    private String imageUrl;
    @Column(name = "email_verified")
    private Boolean emailVerified;
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    @Column(name = "provider_id")
    private String providerId;

}
