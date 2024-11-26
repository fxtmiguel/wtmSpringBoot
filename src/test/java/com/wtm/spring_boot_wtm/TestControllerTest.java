package com.wtm.spring_boot_wtm;

import com.wtm.spring_boot_wtm.controller.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ping_ReturnsPong() throws Exception {
        mockMvc.perform(post("/api/test/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pong"));
    }
}
