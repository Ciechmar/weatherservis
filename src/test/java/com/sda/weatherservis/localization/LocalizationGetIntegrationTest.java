package com.sda.weatherservis.localization;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        System.out.println(responseBody);
        // todo use ObjectMapper
    }

    private Localization createNewLocalization() {
        return new Localization().builder()
                .cityName("Gdansk")
                .countryName("Poland")
                .regionName("Pomorskie")
                .latitude("0")
                .longitude("0")
                .build();
    }
}
