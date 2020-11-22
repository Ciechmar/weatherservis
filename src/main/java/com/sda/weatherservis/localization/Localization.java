package com.sda.weatherservis.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cityName; //NotNull
    String regionName;
    String countryName; //NotNull
    Double longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    Double latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N
    Instant createDate; // todo unnecessary
}
