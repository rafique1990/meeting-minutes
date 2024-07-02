package com.example.meetingMinutesdemo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "task")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String description;
    private String assigned_by;
    private Date assigned_on;
    private Date deadline;
    private Double estimated_effort_days;
    private Integer status;
    private Integer priority;
    private Integer meeting_id;
    private Integer project_id;
    private String comments;
    private String changes;
    private String useful_resources;
    private String contact_person;
}
