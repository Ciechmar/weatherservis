package com.sda.weatherservis.weather;

import lombok.Data;

import java.util.List;

@Data
public class ForecastResponseModel {

    private Integer lat;
    private Integer lon;
    private List<SingleForecastResponseModel> daily;

    @Data
    static class SingleForecastResponseModel {
        private Long dt;

        // todo develop
    }
}
