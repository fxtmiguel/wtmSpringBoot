// 1

package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.model.Friends;
import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.repository.IFriendsRepository;
import com.wtm.spring_boot_wtm.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FriendsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFriendsRepository friendsRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        // Ensure users are saved before each test
        user1 = new User();
        user1.setUsername("john_doe");
        user1.setEmail("john.doe@example.com");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setPassword("ermwhat");
        userRepository.save(user1);

        user2 = new User();
        user2.setUsername("jane_doe");
        user2.setEmail("jane.doe@example.com");
        user2.setFirstname("Jane");
        user2.setLastname("Doe");
        user2.setPassword("thesigma");
        userRepository.save(user2);
    }

//    @Test
//    @Transactional
//    public void testAddFriend() {
//        // Add a friend (POST request)
//        String url = "/api/friends/add";
//        String jsonRequest = "{\"username\": \"john_doe\", \"friendUsername\": \"jane_doe\"}";
//
//        // Create headers and set Content-Type to application/json
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Send the POST request and check the response
//        ResponseEntity<Friends> response = restTemplate.exchange(url, HttpMethod.POST,
//                new org.springframework.http.HttpEntity<>(jsonRequest, headers), Friends.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    @Transactional
    public void testSearchUsers() {
        String url = "/api/friends/search?username=doe";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // assert response.getBody() != null;
    }
}
