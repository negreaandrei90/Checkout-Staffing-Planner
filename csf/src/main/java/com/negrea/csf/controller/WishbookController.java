package com.negrea.csf.controller;

import com.negrea.csf.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.service.WishbookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csf/wishbook")
@RequiredArgsConstructor
@Tag(name = "Wishbook", description = "Endpoints regarding entries in wishbook")
public class WishbookController {
    private final WishbookService service;

    @Operation(summary = "Create wish", description = "Employees will create wishes regarding their desired shifts.")
    @PostMapping
    public ResponseEntity<ScheduleWishDtoResponse> createWish(@RequestBody ScheduleWishDtoRequest request) {
        ScheduleWishDtoResponse response = service.createWish(request);
        return ResponseEntity.ok(response);
    }
}
