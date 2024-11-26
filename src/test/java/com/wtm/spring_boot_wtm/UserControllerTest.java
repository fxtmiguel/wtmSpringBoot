// 2

package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.controller.UserController;
import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.model.ResponseMessage;
import com.wtm.spring_boot_wtm.model.LoginResponse;
import com.wtm.spring_boot_wtm.service.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;

    UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userService.findByUsername("testUser")).thenReturn(null);
        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.saveUser(user);

        assertEquals(201, response.getStatusCodeValue()); // 201 Created
        assertEquals(user, response.getBody());
        verify(userService, times(1)).findByUsername("testUser");
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void testLogin_InvalidCredentials() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("wrongPassword");

        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("correctPassword");

        when(userService.findByUsername("testUser")).thenReturn(existingUser);

        ResponseEntity<?> response = userController.login(user);

        assertEquals(401, response.getStatusCodeValue()); // 401 Unauthorized
        assertTrue(response.getBody() instanceof ResponseMessage);
        assertEquals("Invalid credentials", ((ResponseMessage) response.getBody()).getMessage());
        verify(userService, times(1)).findByUsername("testUser");
    }
}
