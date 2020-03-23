package com.practice.weatherapp.controller;

import com.practice.weatherapp.service.ExternalApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
class WeatherControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private ExternalApiService externalApiService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void getWeatherByCityIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weather/london")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentWeather").isNotEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherByCityDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weather/whah")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getWeatherByUserIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/weather/user/johnny2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentWeather").isNotEmpty())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherByUserIsNotFound() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders
                .get("/weather/user/johnny23")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}