package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.Meeting;
import com.example.meetingMinutesdemo.services.MeetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingsController {

    @Autowired
    private MeetingServiceImpl meetingService;

    @GetMapping
    public List<Meeting> getMeetings() {
        return meetingService.getMeetings();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Meeting getMeetingById(@PathVariable Long id) {
        return meetingService.getMeetingById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Meeting createMeeting(@RequestBody final Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMeetingById(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        meetingService.deleteMeetingById(id);
    }

    @PutMapping(value = "{id}")
    public Meeting updateMeeting(@PathVariable Long id, @RequestBody Meeting meeting) {
        return  meetingService.updateMeeting(id,meeting);
    }

}
