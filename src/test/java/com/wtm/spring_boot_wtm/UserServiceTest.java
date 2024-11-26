// 5

package com.wtm.spring_boot_wtm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.wtm.spring_boot_wtm.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.repository.IUserRepository;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchUsers() {
        String username = "test";
        List<User> mockUsers = Arrays.asList(
                createUser(1L, "testUser1", "user1@example.com", "testUser1"),
                createUser(2L, "testUser2", "user2@example.com", "testUser2")
        );

        when(userRepository.searchByUsernameContaining(username)).thenReturn(mockUsers);

        List<User> result = userService.searchUsers(username);

        assertEquals(2, result.size());
        assertEquals("testUser1", result.get(0).getUsername());
        assertEquals("testUser2", result.get(1).getUsername());
        verify(userRepository, times(1)).searchByUsernameContaining(username);
    }

    @Test
    void testSaveUser() {
        User user = createUser(null, "newUser", "new@example.com", "newUser");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L);
            savedUser.setCreateTime(LocalDateTime.now());
            return savedUser;
        });

        User result = userService.saveUser(user);

        assertNotNull(result.getId());
        assertNotNull(result.getCreateTime());
        assertEquals("newUser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername_UserExists() {
        String username = "existingUser";
        User mockUser = createUser(1L, "Existing", "existing@example.com", username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        User result = userService.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindByUsername_UserDoesNotExist() {
        String username = "nonExistingUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User result = userService.findByUsername(username);

        assertNull(result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindAll() {
        List<User> mockUsers = Arrays.asList(
                createUser(1L, "user1", "user1@example.com", "user1"),
                createUser(2L, "user2", "user2@example.com", "user2")
        );

        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
        verify(userRepository, times(1)).findAll();
    }

    private User createUser(Long id, String firstname, String email, String username) {
        User user = new User();
        user.setId(id);
        user.setFirstname(firstname);
        user.setLastname("TestLast");
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword("password");
        user.setAge(25);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }
}
