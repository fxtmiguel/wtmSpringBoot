package com.wtm.spring_boot_wtm.service;

import java.util.List;
import com.wtm.spring_boot_wtm.model.User;

public interface IUserService {
    User saveUser(User user);
    User findByUsername(String username);
    List<User> findAll();
    // User authenticate(String username, String password); // Uncommented authentication method
}
