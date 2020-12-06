package com.sda.weatherservis.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    Weather getWeatherForCity(Long id, LocalDate date) {
        Localization localizationByCityName = localizationGetService.getLocalizationById(id);
        Double lat = localizationByCityName.getLatitude();
        Double lon = localizationByCityName.getLongitude();

//        https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
//        https://api.openweathermap.org/data/2.5/onecall?lat=54.372158&lon=-18.638306&exclude=daily&units=metric&appid=7d575a0bf05787f3bfbe130726631eff ->Przykładowe dla Gdańska

        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.openweathermap.org/data/2.5/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", "hourly") // Ogarnąć dt - deltaTime
                .queryParam("units", "metric") //będzie temp w C a nie K, a prędkość wiatru w m/sec
                .queryParam("appid", apiKey)
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            ForecastResponseModel forecastResponseModel = objectMapper.readValue(response, ForecastResponseModel.class);
            //ToDo:Response to lista ,a nie pojedynczy model. Trzeba to wyciągnać jeden podany dzień wg daty, ale najpierw trzeba to przepisać. !
            Weather weather = weatherMapper.mapModelToWeather(forecastResponseModel); //zamieniam model z Api na moje potrzebne wartości.
            weatherRepository.save(weather); // Prognoza ma być zapisywana w bazie danych.
            return weather;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // todo throw our own exception -> 500 status code
            return null;
        }
    }
}
