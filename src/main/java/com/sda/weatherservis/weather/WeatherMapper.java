package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

@Component
@Validated
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
        /*
      1) ForecastResponse model to Objekt z listą obiektów tupu SingleResponseModel, która potrzebuje
      2) datePeriod to który dzień mam wziać, czyli miejsce w liście (0-6!)

         */
        ForecastResponseModel.SingleForecastResponseModel singleForecastModel = model.getDaily().get(datePeriod);
        Long timestamp = Long.parseLong(singleForecastModel.getDt()) * 1000;
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDate localDate = LocalDate.ofInstant(instant, zoneId);

        return new Weather().builder()
                .humidity(singleForecastModel.getHumidity())
                .pressure(singleForecastModel.getPressure())
                .temp(singleForecastModel.getTemp().getDay())
                .windDirection(singleForecastModel.getWind_deg())
                .windSpeed(singleForecastModel.getWind_speed())
                .date(localDate)
                .localization(localization)
                .build();
    }
}
