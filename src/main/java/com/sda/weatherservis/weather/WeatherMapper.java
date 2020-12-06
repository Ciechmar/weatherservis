package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class WeatherMapper {

    WeatherDTO mapToWeatherDto(Weather weather) {
        return new WeatherDTO().builder()
                .id(weather.getId())
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .temp(weather.getTemp())
                .windDirection(weather.getWindDirection())
                .windSpeed(weather.getWindSpeed())
                .date(weather.getDate())
                .localization(weather.getLocalization())
                .build();
    }

    Weather mapModelToWeather(ForecastResponseModel model, int datePeriod, Localization localization) {
        ForecastResponseModel.SingleForecastResponseModel weather = model.getDaily().get(datePeriod);
        Timestamp timestamp = Timestamp.valueOf(weather.getDt());
        LocalDate date = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new Weather().builder()
                .humidity(weather.getHumidity())
                .pressure(weather.getPressure())
                .temp(weather.getTemp())
                .windDirection(weather.getWind_deg())
                .windSpeed(weather.getWind_speed())
                .date(date)
                .localization(localization)
                .build();
    }
}
