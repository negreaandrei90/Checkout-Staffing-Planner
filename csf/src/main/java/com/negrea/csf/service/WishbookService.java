package com.negrea.csf.service;

import com.negrea.csf.config.security.CurrentUser;
import com.negrea.csf.dto.schedule.request.ScheduleWishDtoRequest;
import com.negrea.csf.dto.schedule.response.ScheduleWishDtoResponse;
import com.negrea.csf.mapper.schedule.ScheduleWishMapper;
import com.negrea.csf.mapper.user.UserMapper;
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
    private final UserMapper userMapper;
    private final CurrentUser currentUser;

    public ScheduleWishDtoResponse createWish(ScheduleWishDtoRequest request) {
        User employee = currentUser.getCurrentUser();
        ScheduleWish newWish = wishMapper.toEntity(request);
        newWish.setUser(employee);  //assign user to wish

        employee.getWishes().add(newWish);  //assign wish to user's collection of wishes
        userRepository.saveAndFlush(employee);

        //search for the newly created wish
        Optional<ScheduleWish> createdWish = wishRepository.findByUserAndDate(employee, request.getDate());

        if(createdWish.isPresent()) {
            ScheduleWishDtoResponse response = wishMapper.toDto(createdWish.get());
            response.setUser(userMapper.toDto(createdWish.get().getUser()));    //setting nested dto

            return response;
        } else {
            throw new RuntimeException("Could not create wish");
        }
    }
}
