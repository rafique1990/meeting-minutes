package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.Participant;
import com.example.meetingMinutesdemo.services.ParticipantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participants")
public class ParticipantsController {

    @Autowired
    private ParticipantServiceImpl participantService;


    @GetMapping
    public List<Participant> list() {
        return participantService.getParticipantList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Participant get(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Participant create(@RequestBody final Participant participant) {
        return participantService.createParticipant(participant);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        participantService.deleteParticipantById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Participant update(@PathVariable Long id, @RequestBody Participant participant) {
        return participantService.updateParticipant( id,participant);
    }
}
