package com.practice.weatherapp.controller;

import com.practice.weatherapp.service.ExternalApiService;
import com.practice.weatherapp.service.WeatherAppService;
import com.practice.weatherapp.wrapper.RestResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/weather")
public class WeatherController {


    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private WeatherAppService weatherAppService;

    @Autowired
    private Logger logger;

    @RequestMapping(value = "/{city}", method = GET)
    public ResponseEntity<RestResponseWrapper> getWeatherByCity(@PathVariable("city") String city) {
        logger.info("Making a call to getWeatherByCity endpoint with parameters - city: {}", city);
        return externalApiService.callApiToGetWeatherByCity(city);
    }

    @RequestMapping(value = "/user/{userName}", method = GET)
    public ResponseEntity<RestResponseWrapper> getWeatherByUser(@PathVariable("userName") String userName) {
        logger.info("Making a call to getWeatherByUser endpoint with parameters - userName: {}", userName);
        String city = weatherAppService.getCityByUserName(userName);
        return externalApiService.callApiToGetWeatherByCity(city);
    }


}
