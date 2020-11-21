package com.sda.weatherservis.localization;

import com.sun.istack.NotNull;
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

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createLocalization(@RequestBody LocalizationDto localizationDto){

        LocalizationDefinition localizationDefinition = mapToLocalizationDefinition(localizationDto);
        // todo wrap a data to eg. LocalizationDefinition and pass to the localizationCreateService
        Localization newLocalization = localizationCreateService.createLocalization(localizationDefinition);
        log.info(newLocalization);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToLocalizationDto(newLocalization));
    }

    private LocalizationDto mapToLocalizationDto(Localization newLocalization) {
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.builder()
                .cityName(newLocalization.getCityName())
                .countryName(newLocalization.getCountryName())
                .latitude(newLocalization.getLatitude())
                .regionName(newLocalization.getRegionName())
                .longitude(newLocalization.getLongitude())
                .build();
        return localizationDto;

    }

    private LocalizationDefinition mapToLocalizationDefinition(LocalizationDto localizationDto) {
        LocalizationDefinition localizationDefinition = new LocalizationDefinition();

        localizationDefinition.builder()
                .cityName(localizationDto.getCityName())
                .countryName(localizationDto.getCountryName())
                .latitude(localizationDto.getLatitude())
                .longitude(localizationDto.getLongitude())
                .regionName(localizationDto.getRegionName())
                .build();

        return localizationDefinition;

    }
}
