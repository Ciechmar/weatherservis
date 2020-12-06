package com.sda.weatherservis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan

public class WeatherservisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherservisApplication.class, args);
    }

}
