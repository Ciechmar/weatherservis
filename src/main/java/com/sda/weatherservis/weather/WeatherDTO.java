package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WeatherDTO {

    Long id;
    Double temp;
    Integer pressure;
    Integer humidity; //wilogtność
    Double windSpeed;
    Integer windDirection;
    LocalDate date;
    Localization localization;
}
