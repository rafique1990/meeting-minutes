package com.example.meetingMinutesdemo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "participant")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long participant_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String email;
    private String phone_number;
    private String skype_id;

 //   @JsonIgnore
//    private List<Meeting> meetings;
//    @ManyToMany(mappedBy = "participants")
//    public List<Meeting> getMeetings() {
//        return meetings;
//    }
//
//    public void setMeetings(List<Meeting> meetings) {
//        this.meetings = meetings;
//    }

}
