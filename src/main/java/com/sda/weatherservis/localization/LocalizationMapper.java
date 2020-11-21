package com.sda.weatherservis.localization;

import org.springframework.stereotype.Component;

@Component
public class LocalizationMapper {

    LocalizationDto mapToLocalizationDto (Localization newLocalization){
        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setId(newLocalization.getId());
        localizationDto.setCityName(newLocalization.getCityName());
        localizationDto.setCountryName(newLocalization.getCountryName());
        localizationDto.setRegionName(newLocalization.getRegionName());
        localizationDto.setLatitude(newLocalization.getLatitude());
        localizationDto.setLongitude(newLocalization.getLongitude());
        return localizationDto;
    }


}
