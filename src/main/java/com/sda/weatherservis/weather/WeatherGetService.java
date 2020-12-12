package com.sda.weatherservis.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherservis.exception.JasonParseException;
import com.sda.weatherservis.exception.NotCorrectBoundryException;
import com.sda.weatherservis.exception.NotFoundException;
import com.sda.weatherservis.localization.Localization;
import com.sda.weatherservis.localization.LocalizationGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
//@ConfigurationProperties("com.sda.weatherservis.api")
public class WeatherGetService {

    @Value("${forecast.openweather.apiKey}")
    private String apiKey;

    private final WeatherRepository weatherRepository;
    private final LocalizationGetService localizationGetService;
    private final WeatherMapper weatherMapper;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final static int MAX_PERIOD = 5;

    Weather getWeatherForCity(Long id, LocalDate date) {
        Localization localizationById = localizationGetService.getLocalizationById(id);
        Double lat = localizationById.getLatitude();
        Double lon = localizationById.getLongitude();

        //        https://api.openweathermap.org/data/2.5/onecall?lat=54.372158&lon=-18.638306&exclude=daily&units=metric&appid=7d575a0bf05787f3bfbe130726631eff ->Przykładowe dla Gdańska

        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.openweathermap.org/data/2.5/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", "hourly,minutely,current") // Ogarnąć dt - deltaTime
                .queryParam("units", "metric") //będzie temp w C a nie K, a prędkość wiatru w m/sec
                .queryParam("appid", apiKey)
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();
        Weather weather;
        int period = date.compareTo(LocalDate.now());
        if (period>MAX_PERIOD){
            throw new NotCorrectBoundryException("Data poza zasięgiem- lepiej zapytaj jasnowidza");
        }
        try {
            ForecastResponseModel forecastResponseModel = objectMapper.readValue(response, ForecastResponseModel.class);
            //zamieniam model z Api na moje potrzebne wartości.

            weather =weatherMapper.mapModelToWeather(forecastResponseModel, period,localizationById);
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(forecastResponseModel));

            weatherRepository.save(weather); // Prognoza ma być zapisywana w bazie danych.
            return weather;
        } catch (JsonProcessingException e) {
            throw new JasonParseException("Problem z przetworzeniem pobranego Jason'a");
        }

    }
}
