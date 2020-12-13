package com.sda.weatherservis.weather;

import com.sda.weatherservis.localization.Localization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double temp;
    Integer pressure;
    Integer humidity; //wilogtność
    Double windSpeed;
    Integer windDirection;
    LocalDate date;

    @ManyToOne
    Localization localization;

}
