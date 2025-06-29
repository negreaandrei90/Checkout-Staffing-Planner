package com.negrea.csf.utils.validator.schedule;

import com.negrea.csf.exception.UserAlreadyHasShiftException;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduleValidator {

    public boolean validateAssignWish(ScheduleWish wish1, ScheduleWish wish2) {
        User employee1 = wish1.getUser();
        User employee2 = wish2.getUser();
        LocalDate date;

        if(validateLocalDateEquals(wish1.getDate(), wish2.getDate())){
            date = wish1.getDate();
            return validateNotSameEmployee(employee1, employee2) &&
                    validateNotAlreadyScheduledForDay(employee1, employee2, date) &&
                    validateSameShift(wish1, wish2);
        } else {
            return false;
        }
    }

    private boolean validateNotSameEmployee(User user1, User user2) {
        return !user1.equals(user2);
    }

    private boolean validateNotAlreadyScheduledForDay(User user1, User user2, LocalDate date) {
        List<ScheduleAssigned> scheduleUser1 = user1.getSchedule();
        List<ScheduleAssigned> scheduleUser2 = user2.getSchedule();

        boolean matchingDate1;
        boolean matchingDate2;

        if(scheduleUser1 == null) {
            matchingDate1 = false;
        } else {
            matchingDate1 = scheduleUser1.stream()
                    .anyMatch(schedule -> schedule.getDate().equals(date));
        }

        if(scheduleUser2 == null) {
            matchingDate2 = false;
        } else {
            matchingDate2 = scheduleUser2.stream()
                    .anyMatch(schedule -> schedule.getDate().equals(date));
        }

        if(matchingDate1) {
            throw new UserAlreadyHasShiftException("Employee: " + user1.getName() + " is already scheduled on " + date);
        } else if(matchingDate2) {
            throw new UserAlreadyHasShiftException("Employee: " + user2.getName() + " is already scheduled on " + date);
        }

        return !matchingDate1 && !matchingDate2 ? true : false;
    }

    private boolean validateSameShift(ScheduleWish wish1, ScheduleWish wish2) {
        return wish1.getShift().equals(wish2.getShift());
    }

    private boolean validateLocalDateEquals(LocalDate date1, LocalDate date2) {
        return date1.isEqual(date2);
    }
}
