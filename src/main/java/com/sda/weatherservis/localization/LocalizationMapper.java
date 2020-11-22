package com.sda.weatherservis.localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization newLocalization) {
        return new LocalizationDto().builder()
                .id(newLocalization.getId())
                .cityName(newLocalization.getCityName())
                .countryName(newLocalization.getCountryName())
                .latitude(newLocalization.getLatitude())
                .regionName(newLocalization.getRegionName())
                .longitude(newLocalization.getLongitude())
                .build();
    }

    LocalizationDefinition mapToLocalizationDefinition(LocalizationDto localizationDto) {
        return new LocalizationDefinition().builder()
                .cityName(localizationDto.getCityName())
                .countryName(localizationDto.getCountryName())
                .latitude(localizationDto.getLatitude())
                .longitude(localizationDto.getLongitude())
                .regionName(localizationDto.getRegionName())
                .build();
    }

     Localization mapToLocalization(LocalizationDefinition localizationDefinition) {
        Localization localization = new Localization();

        return localization.builder()
                .cityName(localizationDefinition.getCityName())
                .countryName(localizationDefinition.getCountryName())
                .latitude(localizationDefinition.getLatitude())
                .regionName(localizationDefinition.getRegionName())
                .longitude(localizationDefinition.getLongitude())
                .build();
    }
}
