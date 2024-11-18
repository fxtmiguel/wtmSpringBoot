package com.wtm.spring_boot_wtm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtm.spring_boot_wtm.repository.IUserRepository;
import com.wtm.spring_boot_wtm.model.User;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

   
    public List<User> searchUsers(String username) {
        return userRepository.searchByUsernameContaining(username);
    }    

    @Override
    public User saveUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
