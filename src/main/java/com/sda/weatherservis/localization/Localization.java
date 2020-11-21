package com.sda.weatherservis.localization;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String cityName; //NotNull
    String reginName; //NotNull
    String countryName;
    String longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    String latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N
    Instant createDate;
}
