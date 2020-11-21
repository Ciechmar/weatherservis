package com.sda.weatherservis.localization;

import com.sda.weatherservis.exception.BadRequestException;
import com.sda.weatherservis.exception.NotCorrectBoundryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        String cityName = localizationDefinition.getCityName();
        String coutryName = localizationDefinition.getCountryName();
        double latitude = Integer.valueOf(localizationDefinition.latitude);
        double longitude = Integer.valueOf(localizationDefinition.longitude);
        if (cityName.isEmpty() && cityName.trim().length() < 2) {
            throw new BadRequestException(" Nazwa miasta nie może być pusta");
        }
        if (coutryName.isEmpty() && coutryName.trim().length() < 2) {
            throw new BadRequestException(" Nazwa kraju nie może być pusta");
        }

        if (longitude < 180 && longitude > -180) {
            throw new NotCorrectBoundryException("Długośc geograficzna musi być między -180 do 180");
        }

        if (latitude < 90 && latitude > -90) {
            throw new NotCorrectBoundryException("Szerokość geograficzna musi być między -90 do 90");
        }


        Localization localization = mapToLocalization(localizationDefinition);

        return localizationRepository.save(localization);
    }

    private Localization mapToLocalization(LocalizationDefinition localizationDefinition) {

        Localization localization = new Localization();
        localization.builder()
                .cityName(localizationDefinition.getCityName())
                .countryName(localizationDefinition.getCountryName())
                .latitude(localizationDefinition.getLatitude())
                .regionName(localizationDefinition.getRegionName())
                .longitude(localizationDefinition.getLongitude())
                .build();

        return localization;
    }


}
