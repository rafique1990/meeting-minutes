package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {
}
