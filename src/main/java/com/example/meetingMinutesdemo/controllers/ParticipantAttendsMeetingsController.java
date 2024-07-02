package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.ParticipantAttendsMeeting;
import com.example.meetingMinutesdemo.services.ParticipantAttendsMeetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participantattendsmeetings")
public class ParticipantAttendsMeetingsController {

    @Autowired
    private ParticipantAttendsMeetingServiceImpl participantAttendsMeetingService;


    @GetMapping
    public List<ParticipantAttendsMeeting> list() {
        return participantAttendsMeetingService.getParticipantAttendsMeetingList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ParticipantAttendsMeeting get(@PathVariable Long id) {
        return participantAttendsMeetingService.getParticipantAttendsMeetingById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantAttendsMeeting create(@RequestBody final ParticipantAttendsMeeting participantAttendsMeeting) {
        return participantAttendsMeetingService.createParticipantAttendsMeeting(participantAttendsMeeting);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {

        participantAttendsMeetingService.deleteParticipantAttendsMeetingById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ParticipantAttendsMeeting update(@PathVariable Long id, @RequestBody ParticipantAttendsMeeting participantAttendsMeeting) {
    return  participantAttendsMeetingService.updateParticipantAttendsMeeting(id,participantAttendsMeeting);

    }

}

