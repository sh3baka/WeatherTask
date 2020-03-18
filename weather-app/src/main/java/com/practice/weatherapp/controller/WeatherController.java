package com.practice.weatherapp.controller;

import com.practice.weatherapp.model.CurrentWeather;
import com.practice.weatherapp.service.ExternalApiService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private Logger logger;

    @RequestMapping("/{city}")
    public ResponseEntity<CurrentWeather> getWeatherByCity(@PathVariable("city") String city) {
        logger.info("Making a call to getWeatherByCity endpoint with parameters - city: {}", city);
        return externalApiService.callApiToGetWeatherByCity(city);
    }
}
