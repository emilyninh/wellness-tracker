package com.emilyninh.wellness_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class WellnessLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityType;

    private LocalDate date;

    @Min(1)
    @Max(10)
    private int moodRating;

    @Min(1)
    @Max(10)
    private int energyLevel;

    private String notes;
}
