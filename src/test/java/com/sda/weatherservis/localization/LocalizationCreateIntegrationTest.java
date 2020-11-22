package com.sda.weatherservis.localization;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class LocalizationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper(); //ToDo:Doczytać

    @Test
    void createLocalization_createNewLocalizationAndReturn201StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "panstwo", "region", "0", "0");
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder request = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(request).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createLocalization_whenCityIsBlank_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, " ", "panstwo", "region", "0", "0");
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
