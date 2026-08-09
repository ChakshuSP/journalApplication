package com.example.JournalApp.service;

import com.example.JournalApp.api.response.WeatherResponse;
import com.example.JournalApp.cache.AppCache;
import com.example.JournalApp.constans.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather_api_key}")
    private   String apiKey ;
    private static final String api ="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalApi= appCache.APP_CACHE.get(AppCache.keys.weather_api_key).replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY,apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;

    }

    }

