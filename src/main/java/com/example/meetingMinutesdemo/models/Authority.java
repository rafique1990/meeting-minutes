package com.example.meetingMinutesdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Data
@Entity(name = "authorities")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authority_id;
    private String username;
    private Long role_id;
    private String  authority ;
}
