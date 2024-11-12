package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtm.spring_boot_wtm.service.*;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user){
        System.out.println(user.toString());
        if (userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }   


}
