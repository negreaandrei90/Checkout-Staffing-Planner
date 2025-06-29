package com.negrea.csf.controller;

import com.negrea.csf.dto.schedule.response.ScheduleResponse;
import com.negrea.csf.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/csf/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    @GetMapping
    public ResponseEntity<ScheduleResponse> getSchedule(@RequestParam LocalDate date) {
        ScheduleResponse response = service.getScheduleOfDay(date);
        return ResponseEntity.ok(response);
    }
}
