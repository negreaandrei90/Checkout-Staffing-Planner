package com.negrea.csf.controller;

import com.negrea.csf.model.dto.schedule.ShiftDto;
import com.negrea.csf.service.PlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csf/planning")
@RequiredArgsConstructor
public class PlanningController {
    private final PlanningService service;

    @PostMapping
    //@PreAuthorize
    public ResponseEntity<ShiftDto> assignWish(@PathVariable Long wishId1, @PathVariable Long wishId2) {
        ShiftDto response = service.assignWish(wishId1, wishId2);
        return ResponseEntity.ok(response);
    }
}
