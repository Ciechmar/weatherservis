package com.sda.weatherservis.localization;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationDefinition {

    Long id;
    String cityName; //NotNull
    String countryName;//NotNull
    String regionName;
    Double longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    Double latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N

}
