package com.negrea.csf.controller;

import com.negrea.csf.dto.schedule.response.ScheduleResponse;
import com.negrea.csf.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Schedule Management", description = "Endpoints regarding the schedule")
public class ScheduleController {
    private final ScheduleService service;

    @Operation(summary = "Get schedule of given day", description = "Provides the entire schedule of the queries date, consisting of early shift and late shift.")
    @GetMapping
    public ResponseEntity<ScheduleResponse> getSchedule(@RequestParam LocalDate date) {
        ScheduleResponse response = service.getScheduleOfDay(date);
        return ResponseEntity.ok(response);
    }
}
