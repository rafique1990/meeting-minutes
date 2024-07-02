package com.example.meetingMinutesdemo.services;
import com.example.meetingMinutesdemo.models.Participant;

import java.util.List;

public interface ParticipantService {

    public abstract List<Participant> getParticipantList();
    public abstract  Participant getParticipantById(Long id);
    public abstract  Participant createParticipant( final  Participant participant);
    public abstract  void   deleteParticipantById(Long id);
    public abstract  Participant updateParticipant( Long id, Participant participant);
}
