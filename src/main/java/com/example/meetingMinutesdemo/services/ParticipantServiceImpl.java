package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Participant;
import com.example.meetingMinutesdemo.repositories.ParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipantServiceImpl implements  ParticipantService{

    @Autowired
    private ParticipantRepository participantRepository;
    @Override
    public List<Participant> getParticipantList() {

        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(Long id) {
        return participantRepository.getOne(id);
    }

    @Override
    public Participant createParticipant(Participant participant) {
        return participantRepository.saveAndFlush(participant);
    }

    @Override
    public void deleteParticipantById(Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        participantRepository.deleteById(id);
    }

    @Override
    public Participant updateParticipant(Long id, Participant participant) {
        Participant existingParticipant = participantRepository.getOne(id);
        BeanUtils.copyProperties(participant, existingParticipant, "participant_id");
        return participantRepository.saveAndFlush(existingParticipant);
    }
}
