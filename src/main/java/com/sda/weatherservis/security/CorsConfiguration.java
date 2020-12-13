package com.sda.weatherservis.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("dev") //będzie odpalane tylko gdy będzie odpalane w profilu "dev" (nazwy moga być jakiekolwiek se wymysle)- definiuję to w application.properties.
//Można to umieszczac nad komponetami, wtedy są widoczne tylko dla danego profilu,
//Można nad beanami wtedy są tworzone tylko w danym profilu
//Można zrobić application-dev.properties wtedy te ustawienia są używane tylko w profilu aktywnym "dev"
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200");
    }
}
