// 1

package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.model.User;
import com.wtm.spring_boot_wtm.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FriendsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IUserRepository userRepository;


    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
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

    @Test
    @Transactional
    void testSearchUsers() {
        String url = "/api/friends/search?username=doe";
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // assert response.getBody() != null;
    }
}
