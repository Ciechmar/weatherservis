package com.sda.weatherservis.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherservis.exception.NotFoundException;
import com.sda.weatherservis.localization.Localization;
import com.sda.weatherservis.localization.LocalizationGetService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@ConfigurationProperties("com.sda.weatherservis.api")
public class WeatherGetService {

    private Double lat; //szerokość geograficzna
    private Double lon; //długość geograficzna
    private final String EXCLUDE = "&exclude=daily&appid"; //Pobieramy pogodę dzienną na podaną datę lub na dzień następny.
    private final String URL;
    private final String apiKey;


//Pełny adres pod który mam się odwołać ze zmiennymi
// https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
//Przykłąd:
//https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,daily&appid={API key}


    private final WeatherRepository weatherRepository;
    private final LocalizationGetService localizationGetService;
    private final WeatherMapper weatherMapper;
    RestTemplate restTemplate;

    WeatherDTO getWeatherForCity(String locationName) {

        String apiBody;
        Weather weather = null;

        Localization localizationByCityName = localizationGetService.getLocalizationByCityName(locationName);
        lat = localizationByCityName.getLatitude();
        lat = localizationByCityName.getLongitude();

        apiBody = restTemplate.getForEntity(URL + lat + "&" + lon + EXCLUDE + apiKey, String.class).getBody();

        try {
            weather = new ObjectMapper().readValue(apiBody, Weather.class); // ToDo: Nie może być ,bo nie są 1:1 ... zrób mappera do tego
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        saveForecastToDB(weather); // Prognoza ma być zapisywana w bazie danych.

        return weatherMapper.mapToWeatherDto(weather);
    }

    //Servis ma pobrac dane z zewnętrznego API, wyświetlić i zapisać do bazy danych.
//    Weather getWeatherForCity(String locationName) {
//        return weatherRepository.findByName(locationName)
//                .orElseThrow(() -> new NotFoundException("Nie znaleziono prognozy pogody dla lokalizacji " + locationName));
//    }

    public Weather GetWeatherForCityAndDate(String locationName, String date) {
        return weatherRepository.findByNameAndDate(locationName, date)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono prognozy pogody dla lokalizacji " + locationName));

    }

    private void saveForecastToDB(Weather weather) {
        weatherRepository.save(weather);
    }
}
