package com.practice.weatherapp.controller;

import com.practice.weatherapp.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    ExternalApiService externalApiService;

    @RequestMapping("/{city}")
    public ResponseEntity<String> getWeatherByCity(@PathVariable("city") String city){
        return externalApiService.callApiToGetWeatherByCity(city);
    }
}
