package com.practice.weatherapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.weatherapp.model.Location;
import com.practice.weatherapp.model.User;
import com.practice.weatherapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private List<Location> allLocations = Arrays.asList(
            new Location((long)1, "gb", "london"),
            new Location((long)2, "gb", "manchester"));
    private List<User> allUsers = Arrays.asList(
            new User((long)1003, "johnny2", "John", "Doe", allLocations.get(1)),
            new User((long)1002, "jane24", "Jane", "Doe", allLocations.get(1)),
            new User((long)1001, "boss", "Baby", "Doe", allLocations.get(0)));


    @Test
    void getByUsername() throws Exception {
        User expectedUser = new User(1003L, "johnny2", "John", "Doe", allLocations.get(1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/" + expectedUser.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        User userFound = userRepository.findByName("John");

        assertEquals(expectedUser.getId(), userFound.getId());
        assertEquals(expectedUser.getUserName(), userFound.getUserName());
        assertEquals(expectedUser.getName(), userFound.getName());
        assertEquals(expectedUser.getSurname(), userFound.getSurname());
        assertEquals(expectedUser.getLocation().getCity(), userFound.getLocation().getCity());
        assertEquals(expectedUser.getLocation().getCountry(), userFound.getLocation().getCountry());
    }

    @Test
    void getByUsernameNoUserFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/null")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveUser() throws Exception {
    User userToCreate = new User( 1L, "jim26", "Jim", "Doe", allLocations.get(0));

    mockMvc.perform(MockMvcRequestBuilders
            .post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(userToCreate)))
            .andExpect(status().isCreated());

    User actualUserCreated = userRepository.findById(1L).get();

    assertEquals(userToCreate.getId(), actualUserCreated.getId());
    assertEquals(userToCreate.getUserName(), actualUserCreated.getUserName());
    assertEquals(userToCreate.getName(), actualUserCreated.getName());
    assertEquals(userToCreate.getSurname(), actualUserCreated.getSurname());
    assertEquals(userToCreate.getLocation().getCity(), actualUserCreated.getLocation().getCity());
    assertEquals(userToCreate.getLocation().getCountry(), actualUserCreated.getLocation().getCountry());
}

    @Test
    void deleteUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/Jim"))
                .andExpect(status().isOk());

        List<User> actualUsers = userRepository.findAll();

        /*Improve test to check actual values of both lists*/
        assertEquals(allUsers.size(), actualUsers.size());
    }

    /*auxiliary methods*/

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @EnableWebMvc
    static class TestConfig implements WebMvcConfigurer {
        @Bean
        UserRepository mockRepo() {
            return mock(UserRepository.class);
        }

    }
}