package com.emilyninh.wellness_tracker.controller;

import com.emilyninh.wellness_tracker.model.WellnessLogEntity;
import com.emilyninh.wellness_tracker.service.WellnessLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/wellness")
public class WellnessLogController {
    @Autowired
    private WellnessLogService service;

    @PostMapping
    public ResponseEntity<WellnessLogEntity> addLog(@RequestBody @Valid WellnessLogEntity log) {
        return ResponseEntity.ok(service.createLog(log));
    }

    @GetMapping
    public List<WellnessLogEntity> getAll() {
        return service.getAllLogs();
    }

    @GetMapping("/date/{date}")
    public List<WellnessLogEntity> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        return service.getLogsByDate(date);
    }
}
