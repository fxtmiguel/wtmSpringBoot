package com.wtm.spring_boot_wtm.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wtm.spring_boot_wtm.model.*;

public interface IUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
