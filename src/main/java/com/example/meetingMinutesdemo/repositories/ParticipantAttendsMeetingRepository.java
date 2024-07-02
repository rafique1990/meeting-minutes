package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.ParticipantAttendsMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantAttendsMeetingRepository extends JpaRepository<ParticipantAttendsMeeting, Long> {
}
