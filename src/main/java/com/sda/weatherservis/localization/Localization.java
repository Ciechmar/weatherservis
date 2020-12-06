package com.sda.weatherservis.localization;

import com.sda.weatherservis.weather.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
    @Column(nullable = false)
    String cityName; //NotNull
//ToDo: opakować w Optional
    String regionName;
    @Column(nullable = false)
    String countryName; //NotNull
    @Column(nullable = false)
    Double longitude; //długość geograficzna zgodnie z wartościami geograficznymi -180->W, 180->E
    @Column(nullable = false)
    Double latitude; //szerokośc geograficzna zgodna z wartościami geograficznymi -90->S, 90->N

    @OneToMany(mappedBy = "localization", cascade = CascadeType.PERSIST)
    List<Weather> forecastList = new ArrayList<>();
}
