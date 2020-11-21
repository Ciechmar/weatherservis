package com.sda.weatherservis.localization;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {

    LocalizationDto mapToLocalizationDto(Localization newLocalization) {
        LocalizationDto localizationDto = new LocalizationDto();
        newLocalization.builder()
                .cityName(newLocalization.getCityName())
                .countryName(newLocalization.getCountryName())
                .latitude(newLocalization.getLatitude())
                .regionName(newLocalization.getRegionName())
                .longitude(newLocalization.getLongitude())
                .build();
        return localizationDto;
    }


}
