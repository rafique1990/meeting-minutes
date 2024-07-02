package com.example.meetingMinutesdemo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "meeting_assigns_task")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class MeetingAssignsTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meeting_assigns_task_id;
    private Integer task_id;
    private Integer meeting_id;
    private Integer project_id;
}
