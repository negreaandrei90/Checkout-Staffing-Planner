package com.negrea.csf.service;

import com.negrea.csf.model.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.model.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.model.mapper.schedule.ScheduleWishMapper;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishbookService {
    private final UserRepository userRepository;
    private final ScheduleWishRepository wishRepository;
    private final ScheduleWishMapper wishMapper;

    public ScheduleWishDtoResponse createWish(ScheduleWishDtoRequest request) {
        ScheduleWish newWish = wishRepository.save(wishMapper.toEntity(request));
        return wishMapper.toDto(newWish);
    }
}
