package com.example.JournalApp.api.response;

import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class WeatherResponse {


    private  Current current;
    @Data
    public class Current {
        @JsonProperty("observation_time")
        private String observationTime;

        private Integer temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private Integer feelslike;


        @JsonProperty("is_day")
        private String isDay;
    }

}
