package com.sda.weatherservis.weather;

import lombok.Data;

@Data
public class ForecastResponseModel {
//   Tutaj jak wyglądaja pola zebrane z API -> Ogarnij deltaTime DT!

    Long id;
    Double temp;
    Double pressure;
    Double humidity; //wilogtność
    Double windSpeed; //wind_speed
    Double windDirection; //wind_deg

}
