package com.negrea.csf.controller;

import com.negrea.csf.model.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.model.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.service.WishbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csf/wishbook")
@RequiredArgsConstructor
public class WishbookController {
    private final WishbookService service;

    @PostMapping
    public ResponseEntity<ScheduleWishDtoResponse> createWish(@RequestBody ScheduleWishDtoRequest request) {
        ScheduleWishDtoResponse response = service.createWish(request);
        return ResponseEntity.ok(response);
    }
}
