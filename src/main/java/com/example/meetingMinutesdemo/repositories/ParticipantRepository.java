package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
