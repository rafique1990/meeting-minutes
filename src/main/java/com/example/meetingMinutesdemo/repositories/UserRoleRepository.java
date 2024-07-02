package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
}
