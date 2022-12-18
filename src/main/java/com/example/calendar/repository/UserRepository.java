package com.example.calendar.repository;


import com.example.calendar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    Optional<User> getUserByUsername(String username);
}
