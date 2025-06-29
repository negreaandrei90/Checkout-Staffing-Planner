package com.negrea.csf.controller;

import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.service.PlanningService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/csf/planning")
@RequiredArgsConstructor
@Tag(name = "Planning", description = "Endpoints regarding planning shifts")
public class PlanningController {
    private final PlanningService service;

    @Operation(summary = "Assign employees to shift", description = "Admin users assign wish book entries to the schedule, ensuring each employee has only one shift per day. The plan includes two employees for each shift.")
    @PostMapping
    public ResponseEntity<ShiftDto> assignWish(@RequestParam("wish1") Long wishId1, @RequestParam("wish2") Long wishId2) {
        ShiftDto response = service.assignWish(wishId1, wishId2);
        return ResponseEntity.ok(response);
    }
}
