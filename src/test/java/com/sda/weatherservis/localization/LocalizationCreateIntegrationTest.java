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
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "panstwo", "region", 0.0, 0.0);
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
    void createLocalization_whenCityIsEmpty_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "", "panstwo", "region", 0.0, 0.0);
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

    @Test
    void createLocalization_whenCityIsBlank_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "   ", "panstwo", "region", 0.0, 0.0);
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

    @Test
    void createLocalization_whenCountryIsBlank_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "   ", "region", 0.0, 0.0);
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

    @Test
    void createLocalization_whenLongitudeIsNull_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", null, 0.0);
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

    @Test
    void createLocalization_whenLongitudeIs180_returns200StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 180.0, 0.0);
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createLocalization_whenLongitudeIsMinus180_returns200StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", -180.0, 0.0);
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    void createLocalization_whenLatitudeIs90_returns200StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 0.0, 90.0);
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createLocalization_whenLatitudeIsMinus90_returns200StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 0.0, -90.0);
        String requestBody = objectMapper.writeValueAsString(localizationDto);
        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);
        //when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void createLocalization_whenLatitudeIsNull_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 0.0, null);
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

    @Test
    void createLocalization_whenLatitudeIsOver90_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 0.0, 98.0);
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

    @Test
    void createLocalization_whenLatitudeIsUnderMinus90_returns400StatusCode() throws Exception {
        //given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto(null, "miasto", "państwo", "region", 0.0, -98.0);
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
