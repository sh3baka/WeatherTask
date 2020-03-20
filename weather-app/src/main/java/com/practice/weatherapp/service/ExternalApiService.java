package com.practice.weatherapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.practice.weatherapp.model.CurrentWeather;
import com.practice.weatherapp.wrapper.RestResponseWrapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final String UNITS = "metric";

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Logger logger;

    @Cacheable("weatherApi")
    public ResponseEntity<RestResponseWrapper> callApiToGetWeatherByCity(String city) {
        JsonNode jsonNode;
        CurrentWeather currentWeather = null;

        String getWeatherByCityUrl =
                "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + UNITS +
                        "&appid=" + apiKey;

        try {
            logger.info("Making a call to external weather API - city: {}", city.toLowerCase());
            jsonNode = restTemplate.getForObject(getWeatherByCityUrl, JsonNode.class);
        } catch (HttpClientErrorException e) {
            logger.error("There was an error when making a call to external weather API: {}",
                    e.getResponseBodyAsString());
            return new ResponseEntity<>(new RestResponseWrapper(currentWeather, e.getResponseBodyAsString()),
                    HttpStatus.valueOf(e.getRawStatusCode()));
        }

        currentWeather = mapJsonToCurrentWeather(jsonNode);

        return new ResponseEntity<>(new RestResponseWrapper(currentWeather, "success"), HttpStatus.OK);
    }

    /*auxiliary methods*/

    private CurrentWeather mapJsonToCurrentWeather(JsonNode jsonNode) {
        return new CurrentWeather.Builder()
                .mainWeather(jsonNode.get("weather").get(0).get("main").asText())
                .mainWeatherDescription(jsonNode.get("weather").get(0).get("description").asText())
                .temperature(jsonNode.get("main").get("temp").asInt())
                .feelsLikeTemperature(jsonNode.get("main").get("feels_like").asInt())
                .windSpeed(jsonNode.get("wind").get("speed").asInt())
                .windDirectionDeg(jsonNode.get("wind").get("deg").asInt())
                .cloudinessPercentage(jsonNode.get("clouds").get("all").asInt())
                .build();
    }

}
