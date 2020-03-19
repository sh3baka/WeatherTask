package com.practice.weatherapp.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
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
    private
    CacheManager cacheManager;

    @CacheEvict(value = "weatherApi", allEntries = true)
    private void clearingWeatherApicache(){
        logger.info("Evicting all entries from weatherApi cache");
    }

    @Scheduled(cron = "${caching.cron.timer}")
    public void scheduleCachingNewApiData(){
        clearingWeatherApicache();
        logger.info("Getting new cache:");
        externalApiService.callApiToGetWeatherByCity("riga");
        externalApiService.callApiToGetWeatherByCity("stavanger");
    }


}
