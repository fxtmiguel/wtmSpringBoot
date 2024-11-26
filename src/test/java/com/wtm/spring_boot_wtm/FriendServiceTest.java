// 5

package com.wtm.spring_boot_wtm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.wtm.spring_boot_wtm.service.FriendService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wtm.spring_boot_wtm.model.Friends;
import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.repository.IFriendsRepository;
import com.wtm.spring_boot_wtm.repository.IUserRepository;

class FriendServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IFriendsRepository friendshipRepository;

    @InjectMocks
    private FriendService friendService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFriend_ShouldAddFriendSuccessfully() {
        String username = "user1";
        String friendUsername = "friend1";

        User user = new User();
        user.setUsername(username);

        User friend = new User();
        friend.setUsername(friendUsername);

        Friends friendship = new Friends();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendship.setCreateTime(LocalDateTime.now());

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername(friendUsername)).thenReturn(Optional.of(friend));
        when(friendshipRepository.save(any(Friends.class))).thenReturn(friendship);

        Friends result = friendService.addFriend(username, friendUsername);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(friend, result.getFriend());
        verify(friendshipRepository).save(any(Friends.class));
    }

    @Test
    void addFriend_ShouldThrowException_WhenUserNotFound() {
        String username = "user1";
        String friendUsername = "friend1";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> friendService.addFriend(username, friendUsername));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void addFriend_ShouldThrowException_WhenFriendNotFound() {
        String username = "user1";
        String friendUsername = "friend1";

        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userRepository.findByUsername(friendUsername)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> friendService.addFriend(username, friendUsername));
        assertEquals("Friend not found", exception.getMessage());
    }

    @Test
    void getFriends_ShouldReturnListOfFriends() {
        String username = "user1";

        User user = new User();
        user.setUsername(username);

        User friend1 = new User();
        friend1.setUsername("friend1");

        User friend2 = new User();
        friend2.setUsername("friend2");

        Friends friendship1 = new Friends();
        friendship1.setUser(user);
        friendship1.setFriend(friend1);

        Friends friendship2 = new Friends();
        friendship2.setUser(user);
        friendship2.setFriend(friend2);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(friendshipRepository.findByUser(user)).thenReturn(Arrays.asList(friendship1, friendship2));

        List<User> friends = friendService.getFriends(username);

        assertNotNull(friends);
        assertEquals(2, friends.size());
        assertTrue(friends.contains(friend1));
        assertTrue(friends.contains(friend2));
    }

    @Test
    void getFriends_ShouldThrowException_WhenUserNotFound() {
        String username = "user1";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> friendService.getFriends(username));
        assertEquals("User not found", exception.getMessage());
    }
}
