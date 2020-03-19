package com.practice.weatherapp.service;

import com.practice.weatherapp.configuration.CachingConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CacheShedulerService {

    @Autowired
    private Logger logger;

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private CachingConfig cachingConfig;

    @Scheduled(cron = "${caching.cron.timer}")
    public void scheduleCachingNewApiData(){
        cachingConfig.clearingWeatherApicache();
        logger.info("Getting new cache:");
        externalApiService.callApiToGetWeatherByCity("riga");
        externalApiService.callApiToGetWeatherByCity("stavanger");
    }


}
