package com.sda.weatherservis.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class WeatherController {

    final WeatherGetService weatherGetService;
    final WeatherMapper weatherMapper;

//Użtkownik sprawdza pogodę dla danej daty ,a jak jej nie poda to na dzięń następny
    @GetMapping("/weather/{locationName}") //podane bez daty
    ResponseEntity<WeatherDTO> getWeaher (@PathVariable String locationName){
        return ResponseEntity.status(HttpStatus.OK).body(weatherGetService.getWeatherForCity(locationName));
    }

    @GetMapping("/weather/{localizationName}&{date}") //podane z datą
    ResponseEntity<WeatherDTO> getWeaher (@PathVariable String locationName, String date){
        return ResponseEntity.status(HttpStatus.OK)
                .body(weatherMapper
                        .mapToWeatherDto(weatherGetService.GetWeatherForCityAndDate(locationName, date))
                );
    }
}
