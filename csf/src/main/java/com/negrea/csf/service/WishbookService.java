package com.negrea.csf.service;

import com.negrea.csf.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.mapper.schedule.ScheduleWishMapper;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishbookService {
    private final UserRepository userRepository;
    private final ScheduleWishRepository wishRepository;
    private final ScheduleWishMapper wishMapper;

    public ScheduleWishDtoResponse createWish(ScheduleWishDtoRequest request) {
        Optional<User> employee = userRepository.findById(request.getUserId());
        ScheduleWish newWish = wishRepository.save(wishMapper.toEntity(request));

        if(employee.isPresent()) {
            employee.get().getWishes().add(wishMapper.toEntity(request));
            userRepository.save(employee.get());

            return wishMapper.toDto(newWish);
        }

        return null;
    }
}
