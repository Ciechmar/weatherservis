package com.sda.weatherservis.localization;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Log4j2
@RequiredArgsConstructor
public class LocalizationController {

    final LocalizationCreateService localizationCreateService;
    final LocalizationGetService localizationGetService;
    final LocalizationMapper localizationMapper;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createLocalization(@RequestBody LocalizationDto localizationDto) {
        LocalizationDefinition localizationDefinition = localizationMapper.mapToLocalizationDefinition(localizationDto);
        Localization newLocalization = localizationCreateService.createLocalization(localizationDefinition);
        log.info(newLocalization);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(newLocalization));
    }

    @GetMapping("/localizations")
    List<LocalizationDto> getAllLocalization() {
        return localizationGetService.getAllLocalizationList().stream()
                .map(localizationMapper::mapToLocalizationDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/localization/{id}")
    LocalizationDto getLocalization(@PathVariable String id) {
        //ToDo:Zabezpieczenie gdy id nie jest cyfrą-> wyjątek
        Localization localization = localizationGetService.getLocalizationById(Long.valueOf(id));
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @GetMapping("/localization/{locationName}")
    LocalizationDto getLocalizationByName(@PathVariable String locationName) {
        //ToDo:Zabezpieczenie gdy id nie jest cyfrą-> wyjątek
        Localization localization = localizationGetService.getLocalizationByLocationName(locationName);
        return localizationMapper.mapToLocalizationDto(localization);
    }
}
