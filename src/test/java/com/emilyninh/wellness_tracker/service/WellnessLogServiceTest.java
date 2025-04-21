package com.emilyninh.wellness_tracker.service;

import com.emilyninh.wellness_tracker.model.WellnessLogEntity;
import com.emilyninh.wellness_tracker.repository.WellnessLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WellnessLogServiceTest {

    @Mock
    private WellnessLogRepository repository;

    @InjectMocks
    private WellnessLogService subject;

    private WellnessLogEntity log;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        log = new WellnessLogEntity();
        log.setId(1L);
        log.setDate(LocalDate.of(2025, 4, 21));
        log.setActivityType("Pilates");
        log.setMoodRating(5);
        log.setEnergyLevel(6);
        log.setNotes("notes");
    }

    @DisplayName("getAllLogs - calls repo to return list of logs")
    @Test
    void getAllLogs1() {
        List<WellnessLogEntity> expected = List.of(log, new WellnessLogEntity());
        when(repository.findAll()).thenReturn(expected);

        List<WellnessLogEntity> actual = subject.getAllLogs();

        verify(repository).findAll();
        assertEquals(2, actual.size());
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("createLog - calls repo to save log and return log")
    @Test
    void createLog1() {
        when(repository.save(any(WellnessLogEntity.class))).thenReturn(log);

        WellnessLogEntity actual = subject.createLog(log);

        verify(repository).save(log);
        assertNotNull(actual);
        assertEquals("Pilates", actual.getActivityType());
        assertEquals(5, actual.getMoodRating());
        assertEquals(6, actual.getEnergyLevel());
        assertThat(actual).isEqualTo(log);
    }

    @DisplayName("getLogsByDate - calls repo to return logs for a given date")
    @Test
    void getLogsByDate1() {
        LocalDate date = LocalDate.of(2025, 4, 21);
        List<WellnessLogEntity> expected = List.of(log);
        when(repository.findByDate(any(LocalDate.class))).thenReturn(expected);

        List<WellnessLogEntity> actual = subject.getLogsByDate(date);

        verify(repository).findByDate(date);
        assertEquals(date, actual.get(0).getDate());
        assertThat(actual).isEqualTo(expected);
    }
}