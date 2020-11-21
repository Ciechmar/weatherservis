package com.sda.weatherservis.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class LocalizationController {
    final LocalizationCreateService localizationCreateService;
    final LocalizationMapper localizationMapper;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createEntry (@RequestBody LocalizationDto localizationDto){

        String cityName = localizationDto.getCityName();
        String countryName = localizationDto.getCountryName();

        Localization newLocalization = localizationCreateService.createLocalization(cityName, countryName);
        log.info(newLocalization);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizationMapper.mapToLocalizationDto(newLocalization));
    }





}
