package com.practice.weatherapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.practice.weatherapp.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final String UNITS = "metric";

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<CurrentWeather> callApiToGetWeatherByCity(String city) {
        String getWeatherByCityUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + UNITS + "&appid=" + apiKey;
        JsonNode jsonNode = restTemplate.getForObject(getWeatherByCityUrl, JsonNode.class);

        CurrentWeather currentWeather = mapJsonToCurrentWeather(jsonNode);

        return new ResponseEntity<>(currentWeather, HttpStatus.OK);
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
