package com.wtm.spring_boot_wtm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wtm.spring_boot_wtm.model.Friends;
import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.repository.IFriendsRepository;
import com.wtm.spring_boot_wtm.repository.IUserRepository;

@Service
public class FriendService {

    private final IUserRepository userRepository;
    private final IFriendsRepository friendshipRepository;

    public FriendService(IUserRepository userRepository, IFriendsRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public Friends addFriend(String username, String friendUsername) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User friend = userRepository.findByUsername(friendUsername)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        Friends friendship = new Friends();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendship.setCreateTime(LocalDateTime.now());

        return friendshipRepository.save(friendship);
    }

    public List<User> getFriends(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return friendshipRepository.findByUser(user)
                .stream()
                .map(Friends::getFriend)
                .toList(); // Uses Stream.toList() for immutability
    }

}
