package com.wtm.spring_boot_wtm.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wtm.spring_boot_wtm.model.*;

public interface IUserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
    
    @Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
    List<User> searchByUsernameContaining(@Param("username") String username);

}
