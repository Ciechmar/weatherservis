package com.sda.weatherservis.localization;

import com.sda.weatherservis.weather.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
//ToDo: opakować w Optional
    String regionName;
    String countryName; //NotNull
    Double longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    Double latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N

    @OneToMany(mappedBy = "localization")
    List<Weather> forecastList = new ArrayList<>();
}
