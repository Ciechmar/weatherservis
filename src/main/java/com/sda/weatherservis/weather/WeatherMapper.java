package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

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

    Weather mapModelToWeather(ForecastResponseModel model, @Min(0) @Max(6) int datePeriod, Localization localization) {
        /*
      1) ForecastResponse model to Objekt z listą obiektów tupu SingleResponseModel, która potrzebuje
      2) datePeriod to który dzień mam wziać, czyli miejsce w liście (0-6!)

         */
        ForecastResponseModel.SingleForecastResponseModel singleForecastModel = model.getDaily().get(datePeriod);
        Timestamp timestamp = Timestamp.valueOf(singleForecastModel.getDt());
        LocalDate date = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new Weather().builder()
                .humidity(singleForecastModel.getHumidity())
                .pressure(singleForecastModel.getPressure())
//                .temp(singleForecastModel.getTemp().get(0).getDay())
                .temp(20.0)
                .windDirection(singleForecastModel.getWind_deg())
                .windSpeed(singleForecastModel.getWind_speed())
                .date(date)
                .localization(localization)
                .build();
    }
}
