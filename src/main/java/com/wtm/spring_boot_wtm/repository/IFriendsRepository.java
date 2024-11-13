package com.wtm.spring_boot_wtm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wtm.spring_boot_wtm.model.Friends;
import com.wtm.spring_boot_wtm.model.User;

public interface IFriendsRepository extends JpaRepository<Friends, Long>{
    List<Friends> findByUser(User user);
}
