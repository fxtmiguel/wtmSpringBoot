package com.wtm.spring_boot_wtm.service;

import java.util.List;

import com.wtm.spring_boot_wtm.model.*;

public interface IUserService {

    User saveUser(User user);
    User findByUsername(String username);
    List<User> findAll();

}
