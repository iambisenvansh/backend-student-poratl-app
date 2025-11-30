package com.assignment.finalproject.repository;

import com.assignment.finalproject.model.User;
import com.assignment.finalproject.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Optional<User> findByEmail(String email);

    // ranklist ke liye
    List<User> findByTypeOrderByPointsDesc(UserType type);
}
