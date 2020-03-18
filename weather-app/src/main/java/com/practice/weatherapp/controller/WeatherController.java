package com.practice.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{city}")
    public ResponseEntity<String> getWeatherByCity(@PathVariable("city") String city){
        String getWeatherByCityUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        return restTemplate.getForEntity(getWeatherByCityUrl, String.class);
    }
}
