package com.sda.weatherservis.localization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDto {

    Long id;
    String cityName; //NotNull
    String reginName; //NotNull
    String countryName;
    String longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    String latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N
}
