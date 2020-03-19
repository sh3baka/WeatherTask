package com.practice.weatherapp.service;

import com.practice.weatherapp.model.User;
import com.practice.weatherapp.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class WeatherAppService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Logger logger;

    public String getCityByUserName(@PathVariable("userName") String userName) {
        User user = userRepository.findByUserName(userName.toLowerCase());

        if(user == null){
            logger.error("User {} not found", userName);
            return null;
        }
        return user.getLocation().getCity();
    }
}
