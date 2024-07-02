package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
