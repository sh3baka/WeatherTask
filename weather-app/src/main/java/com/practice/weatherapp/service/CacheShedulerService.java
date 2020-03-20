package com.practice.weatherapp.service;

import com.practice.weatherapp.configuration.CachingConfig;
import com.practice.weatherapp.model.Location;
import com.practice.weatherapp.repository.LocationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class CacheShedulerService {

    @Autowired
    private Logger logger;

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private CachingConfig cachingConfig;

    @Autowired
    private LocationRepository locationRepository;

    @Scheduled(cron = "${caching.cron.timer}")
    public void scheduleCachingNewApiData(){
        cachingConfig.clearingWeatherApiCache();
        cacheAllCitiesWithinDatabase();
    }

    private void cacheAllCitiesWithinDatabase(){
        logger.info("Getting new cache for:");
        List<Location> allLocations = locationRepository.findAll();
        allLocations.forEach(location -> externalApiService.callApiToGetWeatherByCity(location.getCity()));
    }


}
