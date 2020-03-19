package com.practice.weatherapp.service;

import com.practice.weatherapp.model.User;
import com.practice.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class WeatherAppService {

    @Autowired
    private UserRepository userRepository;

    public String getCityByUserName(@PathVariable("userName") String userName) {
        User user = userRepository.findByUsername(userName.toLowerCase());
        return user.getLocation().getCity();
    }
}
