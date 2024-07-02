package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.MeetingAssignsTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingAssignsTasksRepository extends JpaRepository<MeetingAssignsTasks, Long> {
}
