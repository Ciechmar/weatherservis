package com.sda.weatherservis.localization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //by zadziałało @CreatedDate
public class Localization {
    //potrzebny do testów, by nie dopisywac CreatedDate czy CreatedBy
    public Localization(Long id, String cityName, String regionName, String countryName, Double longitude, Double latitude) {
        this.id = id;
        this.cityName = cityName;
        this.regionName = regionName;
        this.countryName = countryName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

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

    @CreatedDate
    private Instant createdDate;

    @CreatedBy
    private String createdBy; //meil czy nick użytkowniaka
}
