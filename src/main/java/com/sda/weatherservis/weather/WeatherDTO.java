package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WeatherDTO {

    Long id;
    Double temp;
    Double pressure;
    Double humidity; //wilogtność
    Double windSpeed;
    Double windDirection;
    Localization localization;
}
