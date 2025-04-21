package com.emilyninh.wellness_tracker.repository;

import com.emilyninh.wellness_tracker.model.WellnessLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WellnessLogRepository extends JpaRepository<WellnessLogEntity, Long> {
    List<WellnessLogEntity> findByDate(LocalDate date);
    List<WellnessLogEntity> findByActivityType(String activityType);
}
