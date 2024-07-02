package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepositroy extends JpaRepository<TaskStatus, Long> {
}
