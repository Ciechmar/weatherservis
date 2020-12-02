package com.sda.weatherservis.localization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalizationRepository extends JpaRepository<Localization, Long> {

    Optional<Localization> findByCityName(String locationName);
}
