package com.emilyninh.wellness_tracker.service;

import com.emilyninh.wellness_tracker.model.WellnessLogEntity;
import com.emilyninh.wellness_tracker.repository.WellnessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WellnessLogService {

    @Autowired
    private WellnessLogRepository repository;

    public List<WellnessLogEntity> getAllLogs() { return repository.findAll(); }

    public WellnessLogEntity createLog(WellnessLogEntity log) { return repository.save(log); }

    public List<WellnessLogEntity> getLogsByDate(LocalDate date) { return repository.findByDate(date); }
}
