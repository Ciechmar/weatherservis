package com.sda.weatherservis.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weatherservis.localization.Localization;
import com.sda.weatherservis.localization.LocalizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class WeatherControllerTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    Localization savedLocalization;

    @BeforeEach
    void setUp() {
        localizationRepository.deleteAll();
        savedLocalization = localizationRepository.save(createNewLocalization());
    }


    @Test
    void getForecast_returnsCorrectForecastAnd200StatusCode() throws Exception {
        // given
        String cityName = savedLocalization.getCityName();
        MockHttpServletRequestBuilder request = get("/localization/" + cityName + "/forecast")
                .contentType(MediaType.APPLICATION_JSON);

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    private Localization createNewLocalization() {
        return new Localization().builder()
                .cityName("Gdansk")
                .countryName("Poland")
                .regionName("Pomorskie")
                .latitude(0.0)
                .longitude(0.0)
                .build();
    }
    private Localization createSecondLocalization() {
        return new Localization().builder()
                .cityName("London")
                .countryName("UK")
                .regionName("Greater London")
                .latitude(0.0)
                .longitude(0.0)
                .build();
    }
}