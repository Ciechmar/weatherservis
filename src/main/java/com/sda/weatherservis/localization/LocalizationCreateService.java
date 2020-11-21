package com.sda.weatherservis.localization;

import com.sda.weatherservis.exception.BadRequestException;
//import com.sda.weatherservis.time.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

//    final TimeService timeService;
    final LocalizationRepository localizationRepository;

    Localization createLocalization(String cityName, String coutryName){
        if (cityName.isEmpty()) {
            throw new BadRequestException(" Nazwa miasta nie może być pusta");
        }
        if (coutryName.isEmpty()) {
            throw new BadRequestException(" Nazwa kraju nie może być pusta");
        }
        Localization localization = new Localization();
        localization.setCityName(cityName);
        localization.setCountryName(coutryName);
//        ToDo: LocalizationDefiniton
//        localization.setLatitude();
//        localization.setLongitude();
//        localization.setRegionName();
//        localization.setCreateDate(timeService.getTime());

        return localizationRepository.save(localization);
        }



}
