package com.practice.weatherapp.controller;

import com.practice.weatherapp.model.CurrentWeather;
import com.practice.weatherapp.service.ExternalApiService;
import com.practice.weatherapp.service.WeatherAppService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private WeatherAppService weatherAppService;

    @Autowired
    private Logger logger;

    @RequestMapping("/{city}")
    public ResponseEntity<CurrentWeather> getWeatherByCity(@PathVariable("city") String city) {
        logger.info("Making a call to getWeatherByCity endpoint with parameters - city: {}", city);
        return externalApiService.callApiToGetWeatherByCity(city);
    }

    @RequestMapping("/user/{userName}")
    public ResponseEntity<CurrentWeather> getWeatherByUser(@PathVariable("userName") String userName) {
        logger.info("Making a call to getWeatherByUser endpoint with parameters - userName: {}", userName);
        String city = weatherAppService.getCityByUserName(userName);

        if(city == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return externalApiService.callApiToGetWeatherByCity(city);
    }


}
