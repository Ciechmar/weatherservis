package com.sda.weatherservis.localization;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc

public class LocalizationGetIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper(); //ToDo:Doczytać

    @Test
    void getByIdTest_getOneCorrectLocalization() throws JsonProcessingException {
        //given
        localizationRepository.deleteAll();
        //Tworze 3 obiekty do bazy
        LocalizationDto localizationDto = new LocalizationDto(1L, "miasto", "panstwo", "region", "0", "0");
        LocalizationDto localizationDto2 = new LocalizationDto(2L, "miasto2", "panstwo2", "region2", "2", "2");
        LocalizationDto localizationDto3 = new LocalizationDto(3L, "miasto3", "panstwo3", "region3", "3", "3");

        //Zapisuję
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        String requestBody2 = objectMapper.writeValueAsString(localizationDto2);
        MockHttpServletRequestBuilder post2 = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody2);
        String requestBody3 = objectMapper.writeValueAsString(localizationDto3);
        MockHttpServletRequestBuilder post3 = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody3);

        //when
        //Chcę pobrać z bazy obiekt o id 2:

//        MockHttpServletRequestBuilder result = Collections.get("localization/{2L}")
//                .contentType(MediaType.APPLICATION_JSON);

        //then
//        MockHttpServletResponse response = result.;
//        AssertThat(response.equals(result));


    }


}
