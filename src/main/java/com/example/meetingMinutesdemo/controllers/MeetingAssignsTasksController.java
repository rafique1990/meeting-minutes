package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.MeetingAssignsTasks;
import com.example.meetingMinutesdemo.services.MeetingAssignsTasksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meetingassignstasks")
public class  MeetingAssignsTasksController {

    @Autowired
    private MeetingAssignsTasksServiceImpl meetingAssignsTasksService;

    @GetMapping
    public List<MeetingAssignsTasks> list() {

        return meetingAssignsTasksService.getMeetingAssignsTasksList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public MeetingAssignsTasks get(@PathVariable Long id) {
        return meetingAssignsTasksService.getMeetingAssignsTasksById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MeetingAssignsTasks create(@RequestBody final MeetingAssignsTasks meetingAssignsTasks) {
        return meetingAssignsTasksService.createMeetingAssignsTasks(meetingAssignsTasks);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        meetingAssignsTasksService.deleteMeetingAssignsTasksById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MeetingAssignsTasks update(@PathVariable Long id, @RequestBody MeetingAssignsTasks meetingAssignsTask) {
    return meetingAssignsTasksService.updateMeetingAssignsTasks(id,meetingAssignsTask);

    }


}
