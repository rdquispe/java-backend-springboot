package com.rodrigo.backend.controllers;

import com.rodrigo.backend.models.User;
import com.rodrigo.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createUser() throws Exception {
        String body = "{\n" +
            "    \"name\": \"selena\",\n" +
            "    \"last_name\": \"perez\"\n" +
            "}";
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("http://localhost:9000/api/v1/users")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("selena"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.last_name").value("perez"));

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void getListUsers() throws Exception {
        userRepository.save(new User("name", "last"));
        mockMvc.perform(
                MockMvcRequestBuilders
                    .get("http://localhost:9000/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());

    }

    @Test
    void getUserById() throws Exception {
        User user = userRepository.save(new User("name", "last"));
        String url = String.format("http://localhost:9000/api/v1/users/%d", user.getId());
        mockMvc.perform(
            MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void deleteUserById() throws Exception {
        User user = userRepository.save(new User("name", "last"));
        String url = String.format("http://localhost:9000/api/v1/users/%d", user.getId());
        mockMvc.perform(
                MockMvcRequestBuilders
                    .delete(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}