package com.example.meetingMinutesdemo.services;
import com.example.meetingMinutesdemo.models.ParticipantAttendsMeeting;

import java.util.List;

public interface ParticipantAttendsMeetingService {

    public abstract List<ParticipantAttendsMeeting> getParticipantAttendsMeetingList();
    public abstract  ParticipantAttendsMeeting getParticipantAttendsMeetingById(Long id);
    public abstract  ParticipantAttendsMeeting createParticipantAttendsMeeting( final  ParticipantAttendsMeeting participantAttendsMeeting);
    public abstract  void   deleteParticipantAttendsMeetingById(Long id);
    public abstract  ParticipantAttendsMeeting updateParticipantAttendsMeeting( Long id, ParticipantAttendsMeeting participantAttendsMeeting);
}
