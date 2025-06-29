package com.negrea.csf.service;

import com.negrea.csf.config.security.CurrentUser;
import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.exception.InvalidWishException;
import com.negrea.csf.exception.WishNotFoundException;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import com.negrea.csf.utils.validator.schedule.ScheduleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanningService {
    private final ScheduleWishRepository wishRepository;
    private final UserRepository userRepository;
    private final ScheduleValidator validator;
    private final UserMapper userMapper;
    private final CurrentUser currentUser;

    public ShiftDto assignWish(Long wishId1, Long wishId2) {
        //retrieve wishes with given ID
        Optional<ScheduleWish> wish1 = wishRepository.findById(wishId1);
        Optional<ScheduleWish> wish2 = wishRepository.findById(wishId2);

        if(wish1.isPresent() && wish2.isPresent()) {
            if(validator.validateAssignWish(wish1.get(), wish2.get())) {
                //if validations pass, create ScheduleAssigned entities
                ScheduleAssigned schedule1 = createNewEntity(wish1.get());
                ScheduleAssigned schedule2 = createNewEntity(wish2.get());

                User employee1 = wish1.get().getUser();
                User employee2 = wish2.get().getUser();

                List<User> employees = new ArrayList<>();
                employees.add(employee1);
                employees.add(employee2);

                if(employee1.getSchedule() == null) {
                    employee1.setSchedule(new ArrayList<ScheduleAssigned>());
                }

                employee1.getSchedule().add(schedule1);

                if(employee2.getSchedule() == null) {
                    employee2.setSchedule(new ArrayList<ScheduleAssigned>());
                }

                employee2.getSchedule().add(schedule2);

                userRepository.save(employee1);
                userRepository.save(employee2);

                return ShiftDto.builder()
                        .employees(userMapper.toUserScheduleDtoList(employees))
                        .shift(schedule1.getShift())
                        .date(schedule1.getDate())
                        .build();
            } else {
                throw new InvalidWishException("The wishes cannot be planned together");
            }
        } else {
            throw new WishNotFoundException("Could not find the wishes");
        }
    }

    /*
    Method to convert from Wish to Schedule
     */
    private ScheduleAssigned createNewEntity(ScheduleWish wish) {

        return ScheduleAssigned.builder()
                .date(wish.getDate())
                .shift(wish.getShift())
                .user(wish.getUser())
                .assignedBy(currentUser.getCurrentUser())
                .build();
    }
}
