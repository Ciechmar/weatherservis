package com.sda.weatherservis.localization;

import com.sda.weatherservis.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LocalizationGetService {

    private final LocalizationRepository localizationRepository;

    Localization getLocalizationById(Long id) {
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lokalizacji o id " + id));
    }

    List<Localization> getAllLocalizationList() {
        return localizationRepository.findAll();
    }

    public Localization getLocalizationByLocationName(String locationName) {
        return localizationRepository.findByCityName(locationName)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lokalizacji o nazwie " + locationName));

    }
}
