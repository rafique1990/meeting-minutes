package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailOrUsername(String email,String username);
    Optional<User> findByEmailOrUsername(String email,String username);
    /*************************/
    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
