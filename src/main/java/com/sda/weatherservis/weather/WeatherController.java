package com.sda.weatherservis.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    final WeatherGetService weatherGetService;
    final WeatherMapper weatherMapper;

    //Użytkownik sprawdza pogodę dla danej daty ,a jak jej nie poda to na dzień następny-
    // Założenie jest takie, że lokalizację z danymi geograficznymi musi być w bazie danych, więc posiadam lon i lat.
    @GetMapping("/weather/{id}")
    //podane bez daty czyli na dzień następny
    ResponseEntity<WeatherDTO> getWeaher(@PathVariable Long id, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) { //ToDo: Min i max na dd
        if (date == null) {
            date = LocalDate.now().plusDays(1);
        }
        Weather weather = weatherGetService.getWeatherForCity(id, date);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weatherMapper.mapToWeatherDto(weather));
    }
}
