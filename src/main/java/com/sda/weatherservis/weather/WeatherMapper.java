package com.sda.weatherservis.weather;

import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    WeatherDTO mapToWeatherDto(Weather weather){
        return new WeatherDTO().builder()
                .id(weather.getId())
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .temp(weather.getTemp())
                .windDirection(weather.getWindDirection())
                .windSpeed(weather.getWindSpeed())
                .localization(weather.getLocalization())
                .build();
    }

    Weather mapModelToWeather(ForecastResponseModel weather){
        return new Weather().builder()
                .id(weather.getId())
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .temp(weather.getTemp())
                .windDirection(weather.getWindDirection())
                .windSpeed(weather.getWindSpeed())
                .localization(weather.getLocalization())
                .build();
    }

}
