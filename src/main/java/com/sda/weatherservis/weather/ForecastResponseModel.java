package com.sda.weatherservis.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ForecastResponseModel {

    Double lat;
    Double lon;
    @JsonProperty("daily")
    List<SingleForecastResponseModel> daily;

    @Data
    public static class SingleForecastResponseModel {
        String dt;
        @JsonProperty("temp")
        private DailyTemperature temp;
        Integer pressure;
        Integer humidity;
        Double wind_speed;
        Integer wind_deg; //kierunek wiatru w stopniach.
    }

    @Data
    public static class DailyTemperature {
        private Double day;
        private Double min;
        private Double max;
    }
}
