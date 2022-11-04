package com.software.swastik.project.swastik.connect.repositories;

import com.software.swastik.project.swastik.connect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmailId(String emailId);
}
