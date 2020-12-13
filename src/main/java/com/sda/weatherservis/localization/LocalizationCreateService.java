package com.sda.weatherservis.localization;

import com.sda.weatherservis.exception.BadRequestException;
import com.sda.weatherservis.exception.NotCorrectBoundryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationCreateService {

    final LocalizationRepository localizationRepository;
    final LocalizationMapper localizationMapper;

    Localization createLocalization(LocalizationDefinition localizationDefinition) {
        String cityName = localizationDefinition.getCityName();
        String countryName = localizationDefinition.getCountryName();
        Double latitudeNumber = localizationDefinition.getLatitude();
        Double longitudeNumber = localizationDefinition.getLongitude();

        if (cityName.trim().isEmpty()) {
            throw new BadRequestException(" Nazwa miasta nie może być pusta");
        }
        if (countryName.trim().isEmpty()) {
            throw new BadRequestException(" Nazwa kraju nie może być pusta");
        }
        if (longitudeNumber == null || longitudeNumber > 180.0 || longitudeNumber < -180.0) {
            throw new NotCorrectBoundryException("Długośc geograficzna musi być między -180 do 180");
        }
        if (latitudeNumber == null || latitudeNumber > 90.0 || latitudeNumber < -90.0) {
            throw new NotCorrectBoundryException("Szerokość geograficzna musi być między -90 do 90");
        }
        Localization localization = localizationMapper.mapToLocalization(localizationDefinition);
        return localizationRepository.save(localization);
    }
}
