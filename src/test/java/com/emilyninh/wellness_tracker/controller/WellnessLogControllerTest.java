package com.emilyninh.wellness_tracker.controller;
import com.emilyninh.wellness_tracker.model.WellnessLogEntity;
import com.emilyninh.wellness_tracker.service.WellnessLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(WellnessLogControllerTest.class)
class WellnessLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WellnessLogService service;

    @Autowired private ObjectMapper objectMapper;

    private WellnessLogEntity log;

    @BeforeEach
    void setUp() {
        log = new WellnessLogEntity();
        log.setId(1L);
        log.setDate(LocalDate.of(2025, 4, 21));
        log.setActivityType("Pilates");
        log.setMoodRating(5);
        log.setEnergyLevel(6);
        log.setNotes("Felt great!");
    }

    @DisplayName("addLog - calls service to create wellness log")
    @Test
    void addLog1() throws Exception {

        when(service.createLog(any(WellnessLogEntity.class))).thenReturn(log);

        mockMvc.perform(post("/api/wellness")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(log)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activityType").value("Pilates"))
                .andExpect(jsonPath("$.moodRating").value(5))
                .andExpect(jsonPath("$.energyLevel").value(6));

        verify(service).createLog(log);
    }
}