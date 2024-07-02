package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
