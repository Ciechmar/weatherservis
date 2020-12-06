package com.sda.weatherservis.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherservis.localization.Localization;
import com.sda.weatherservis.localization.LocalizationGetService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@ConfigurationProperties("com.sda.weatherservis.api")
public class WeatherGetService {


    private final WeatherRepository weatherRepository;
    private final LocalizationGetService localizationGetService;
    private final WeatherMapper weatherMapper;
    private final ObjectMapper objectMapper;
    private Double lat; //szerokość geograficzna
    private Double lon; //długość geograficzna
    private final String apiKey;
    RestTemplate restTemplate;

    WeatherDTO getWeatherForCity(Long id, LocalDate date) {
         Weather weather;

        Localization localizationByCityName = localizationGetService.getLocalizationById(id);
        lat = localizationByCityName.getLatitude();
        lon = localizationByCityName.getLongitude();

//        https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
//        https://api.openweathermap.org/data/2.5/onecall?lat=54.372158&lon=-18.638306&exclude=daily&units=metric&appid=7d575a0bf05787f3bfbe130726631eff ->Przykładowe dla Gdańska

        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.openweathermap.org/data/2.5/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", "daily")// Ogarnąć dt - deltaTime
                .queryParam("units", "metric") //będzie temp w C a nie K, a prędkość wiatru w m/sec
                .queryParam("appiid", apiKey)
                .build().toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();
        ForecastResponseModel forecastResponseModel = null;

        try {
            forecastResponseModel = objectMapper.readValue(response, ForecastResponseModel.class);
            //ToDo:Response to lista ,a nie pojedynczy model. Trzeba to wyciągnać jeden podany dzień wg daty, ale najpierw trzeba to przepisać. !
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        weather = weatherMapper.mapModelToWeather(forecastResponseModel); //zamieniam model z Api na moje potrzebne wartości.
        saveForecastToDB(weather); // Prognoza ma być zapisywana w bazie danych.

        return weatherMapper.mapToWeatherDto(weather);
    }

    private void saveForecastToDB(Weather weather) {
        weatherRepository.save(weather);
    }
}
