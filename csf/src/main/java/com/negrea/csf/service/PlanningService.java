package com.negrea.csf.service;

import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.repository.ScheduleAssignedRepository;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import com.negrea.csf.utils.validator.schedule.ScheduleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanningService {
    //private final ScheduleAssignedRepository scheduleRepository;
    private final ScheduleWishRepository wishRepository;
    private final UserRepository userRepository;
    private final ScheduleValidator validator;

    public void assignWish(Long wishId1, Long wishId2) {
        Optional<ScheduleWish> wish1 = wishRepository.findById(wishId1);
        Optional<ScheduleWish> wish2 = wishRepository.findById(wishId2);
        boolean wishAssigned;

        if(wish1.isPresent() && wish2.isPresent()) {
            if(validator.validateAssignWish(wish1.get(), wish2.get())) {
                ScheduleAssigned schedule1 = createNewEntity(wish1.get());
                ScheduleAssigned schedule2 = createNewEntity(wish2.get());

                wish1.get().getUser().getSchedule().add(schedule1);
                wish2.get().getUser().getSchedule().add(schedule2);

                userRepository.save(wish1.get().getUser());
                userRepository.save(wish2.get().getUser());
            } else {
                //not valid
                return;
            }
        } else {
            //no wish
            return;
        }
    }

    private ScheduleAssigned createNewEntity(ScheduleWish wish) {
        ScheduleAssigned schedule = new ScheduleAssigned();
        schedule.setLocalDate(wish.getLocalDate());
        schedule.setShift(wish.getShift());
        schedule.setUser(wish.getUser());
        //TODO: set assigned by logged in user
        return schedule;
    }
}
