package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.ParticipantAttendsMeeting;
import com.example.meetingMinutesdemo.repositories.ParticipantAttendsMeetingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipantAttendsMeetingServiceImpl implements ParticipantAttendsMeetingService {


    @Autowired
    private ParticipantAttendsMeetingRepository participantAttendsMeetingRepository;

    @Override
    public List<ParticipantAttendsMeeting> getParticipantAttendsMeetingList() {

        return participantAttendsMeetingRepository.findAll();
    }

    @Override
    public ParticipantAttendsMeeting getParticipantAttendsMeetingById(Long id) {

        return participantAttendsMeetingRepository.getOne(id);
    }

    @Override
    public ParticipantAttendsMeeting createParticipantAttendsMeeting(ParticipantAttendsMeeting participantAttendsMeeting) {
        return participantAttendsMeetingRepository.saveAndFlush(participantAttendsMeeting);
    }

    @Override
    public void deleteParticipantAttendsMeetingById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        participantAttendsMeetingRepository.deleteById(id);
    }

    @Override
    public ParticipantAttendsMeeting updateParticipantAttendsMeeting(Long id, ParticipantAttendsMeeting participantAttendsMeeting) {
        ParticipantAttendsMeeting existingParticipantAttendsMeeting = participantAttendsMeetingRepository.getOne(id);
        BeanUtils.copyProperties(participantAttendsMeeting, existingParticipantAttendsMeeting, "participant_attends_meeting_id");
        return participantAttendsMeetingRepository.saveAndFlush(existingParticipantAttendsMeeting);
    }
}
