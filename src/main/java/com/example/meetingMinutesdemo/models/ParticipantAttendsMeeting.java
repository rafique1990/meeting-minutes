package com.example.meetingMinutesdemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "participant_attends_meeting")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class ParticipantAttendsMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participant_attends_meeting_id;
    private Integer participant_id;
    private Integer meeting_id;
    private Integer project_id;

}
