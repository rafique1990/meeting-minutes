package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.exception.meeting.MeetingNotFoundException;
import com.example.meetingMinutesdemo.models.Meeting;
import com.example.meetingMinutesdemo.repositories.MeetingRepositorry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepositorry meetingRepositorry;


    @GetMapping
    public List<Meeting> getMeetings() {
        return meetingRepositorry.findAll();
    }


    public Meeting getMeetingById(Long id) {
        return meetingRepositorry.getOne(id);
    }


    public Meeting createMeeting(Meeting meeting) {
        return meetingRepositorry.saveAndFlush(meeting);
    }


    public void deleteMeetingById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        meetingRepositorry.deleteById(id);
    }


    public Meeting updateMeeting(Long id, Meeting meeting) {
        Meeting existingMeeting =null;
        Optional<Meeting> meetingOptional =   meetingRepositorry.findById(id);

        if( meetingOptional.isPresent()){
            existingMeeting = meetingOptional.get();
        }
        else
            throw new MeetingNotFoundException();
        BeanUtils.copyProperties(meeting, existingMeeting, "meeting_id");
        return meetingRepositorry.saveAndFlush(existingMeeting);
    }
}
