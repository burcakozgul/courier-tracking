package com.courier.couriertracking.resource;

import com.courier.couriertracking.model.request.CreateCourierRequest;
import com.courier.couriertracking.model.request.SaveCourierLocationRequest;
import com.courier.couriertracking.service.CourierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourierResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourierService courierService;

    @Test
    void testCreateCourier() throws Exception {
        CreateCourierRequest request = CreateCourierRequest.builder()
                .courierId("1111")
                .name("test")
                .build();

        mockMvc.perform(post("/couriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveCourierLocation() throws Exception {
        SaveCourierLocationRequest request = SaveCourierLocationRequest.builder()
                .courierId(1L)
                .latitude(40.9923309)
                .longitude(29.1244230)
                .currentTime(LocalDateTime.now())
                .build();

        mockMvc.perform(post("/couriers/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}