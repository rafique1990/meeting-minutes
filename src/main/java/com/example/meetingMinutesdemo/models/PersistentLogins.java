package com.example.meetingMinutesdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity(name = "persistent_logins")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class PersistentLogins {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String series;
    private  String username;
    private String token ;
    private Date last_used;
}
