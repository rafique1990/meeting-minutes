package com.example.meetingMinutesdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity(name = "meeting")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_id;

    @Column(name = "meeting_platform")
    private String meetingPlatform;

    private String meeting_link;
    private String meeting_venue;
    private String meeting_agenda;
    private String meeting_minutes;
    private Integer project_id;
    private Date meeting_date;

}
