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
    @JsonProperty ("daily")
     List<SingleForecastResponseModel> daily;

    @Data
    public static class SingleForecastResponseModel {
         String dt;
//        @JsonProperty ("temp")
//        private List<DailyTemperature>temp;
         Double pressure;
         Double humidity;
         Double wind_speed;
         Double wind_deg; //kierunek wiatru w stopniach.
    }

//    @Data
//    public static class DailyTemperature {
//        private Double day;
//        private Double min;
//        private Double max;
//    }
}
