package com.wtm.spring_boot_wtm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wtm.spring_boot_wtm.model.Friends;
import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.service.FriendService;
import com.wtm.spring_boot_wtm.service.UserService;

@RestController
@RequestMapping("api/friends")
public class FriendsController {

    @Autowired
    private FriendService service;
    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String friendUsername = request.get("friendUsername");
        Friends friendship = service.addFriend(username, friendUsername);
        return ResponseEntity.ok(friendship);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<User>> listFriends(@RequestParam String username) {
        List<User> friends = service.getFriends(username);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String username) {
        List<User> users = userService.searchUsers(username);
        return ResponseEntity.ok(users);
    }
}
