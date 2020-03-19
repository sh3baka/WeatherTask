package com.practice.weatherapp.wrapper;

import com.practice.weatherapp.model.CurrentWeather;

public class RestResponseWrapper {
    private CurrentWeather currentWeather;
    private String message;

    public RestResponseWrapper(CurrentWeather currentWeather, String message) {
        this.currentWeather = currentWeather;
        this.message = message;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
