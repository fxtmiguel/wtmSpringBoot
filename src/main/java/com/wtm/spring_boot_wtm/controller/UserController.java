package com.wtm.spring_boot_wtm.controller;

import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.model.ResponseMessage;
import com.wtm.spring_boot_wtm.model.LoginResponse;
import com.wtm.spring_boot_wtm.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private IUserService userService;

    UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Username already exists
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED); // User saved
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser == null) {
            return new ResponseEntity<>(new ResponseMessage("User not found"), HttpStatus.NOT_FOUND);
        }

        
        if (existingUser.getPassword().equals(user.getPassword())) {
            // Return user details without the token
            return new ResponseEntity<>(new LoginResponse(existingUser.getId(), existingUser.getUsername()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

}
