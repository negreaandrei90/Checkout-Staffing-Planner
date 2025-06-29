package com.negrea.csf.controller;

import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.service.PlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/csf/planning")
@RequiredArgsConstructor
public class PlanningController {
    private final PlanningService service;

    @PostMapping
    public ResponseEntity<ShiftDto> assignWish(@RequestParam("wish1") Long wishId1, @RequestParam("wish2") Long wishId2) {
        ShiftDto response = service.assignWish(wishId1, wishId2);
        return ResponseEntity.ok(response);
    }
}
