package com.sda.weatherservis.weather;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ForecastResponseModel {

    private Double lat;
    private Double lon;
    private List<SingleForecastResponseModel> daily;

    @Data
    static class SingleForecastResponseModel {
        private String dt;
        private Double temp;
        private Double pressure;
        private Double humidity;
        private Double wind_speed;
        private Double wind_deg; //kierunek wiatru w stopniach.
    }
}
