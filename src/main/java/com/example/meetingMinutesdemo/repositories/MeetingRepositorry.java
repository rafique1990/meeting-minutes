package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepositorry extends JpaRepository<Meeting, Long> {
}
