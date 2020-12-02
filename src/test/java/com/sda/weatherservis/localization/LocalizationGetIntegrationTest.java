package com.sda.weatherservis.localization;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class LocalizationGetIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper(); //ToDo:Doczytać

    @Test
    void getByIdTest_getOneCorrectLocalization() throws Exception {
        //given
        localizationRepository.deleteAll();
        localizationRepository.save(createNewLocalization());
        localizationRepository.save(createNewLocalization());
        Localization savedLocalization = localizationRepository.save(createNewLocalization());
        Long id = savedLocalization.getId();
        MockHttpServletRequestBuilder request = get("/localization/" + id)
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        LocalizationDto localizationDto = objectMapper.readValue(responseBody, LocalizationDto.class);
        assertThat(localizationDto.getId().equals(id));
        assertThat(localizationDto.getCityName().equals(savedLocalization.getCityName()));
        assertThat(localizationDto.getCountryName().equals(savedLocalization.getCountryName()));
        assertThat(localizationDto.getLatitude().equals(savedLocalization.getLatitude()));
        assertThat(localizationDto.getLongitude().equals(savedLocalization.getLongitude()));
    }

    @Test
    void getByCityNameTest_getOneCorrectLocalization() throws Exception {
        //given
        localizationRepository.deleteAll();
        Localization localization = localizationRepository.save(createNewLocalization());
        localizationRepository.save(createSecondLocalization());
        Localization savedLocalization = localization;
        String cityName = savedLocalization.getCityName();
        MockHttpServletRequestBuilder request = get("/localization/" + cityName)
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseBody = response.getContentAsString();
        LocalizationDto localizationDto = objectMapper.readValue(responseBody, LocalizationDto.class);
        assertThat(localizationDto.getCityName().equals(savedLocalization.getCityName()));
        assertThat(localizationDto.getCountryName().equals(savedLocalization.getCountryName()));
        assertThat(localizationDto.getLatitude().equals(savedLocalization.getLatitude()));
        assertThat(localizationDto.getLongitude().equals(savedLocalization.getLongitude()));
    }


    @Test
    void getAllTest_getCorrectLocalizationList() throws Exception {
        //given
        localizationRepository.deleteAll();
        localizationRepository.save(createNewLocalization());
        localizationRepository.save(createNewLocalization());
        MockHttpServletRequestBuilder request = get("/localizations")
                .contentType(MediaType.APPLICATION_JSON);
        //when
        MvcResult result = mockMvc.perform(request).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        String responseBody = response.getContentAsString();

        List<LocalizationDto> locations = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        assertThat(locations).hasSize(2);
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
