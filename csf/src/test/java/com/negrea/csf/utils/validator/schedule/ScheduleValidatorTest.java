package com.negrea.csf.utils.validator.schedule;

import com.negrea.csf.exception.UserAlreadyHasShiftException;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleValidatorTest {
    private ScheduleValidator validator;
    private User user1;
    private User user2;
    private LocalDate testDate;

    @BeforeEach
    void setUp() {
        validator = new ScheduleValidator();
        testDate = LocalDate.of(2025, 7, 1);

        user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");
        user1.setSchedule(new ArrayList<>());

        user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");
        user2.setSchedule(new ArrayList<>());
    }

    private ScheduleWish createWish(User user, LocalDate date, Shift shift) {
        ScheduleWish wish = new ScheduleWish();
        wish.setUser(user);
        wish.setDate(date);
        wish.setShift(shift);
        return wish;
    }

    @Test
    void validateAssignWish_shouldReturnTrue_whenValid() {
        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user2, testDate, Shift.EARLY);

        boolean result = validator.validateAssignWish(wish1, wish2);

        Assertions.assertTrue(result);
    }

    @Test
    void validateAssignWish_shouldReturnFalse_whenSameUser() {
        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user1, testDate, Shift.EARLY);

        boolean result = validator.validateAssignWish(wish1, wish2);

        Assertions.assertFalse(result);
    }

    @Test
    void validateAssignWish_shouldReturnFalse_whenDifferentDates() {
        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user2, testDate.plusDays(1), Shift.EARLY);

        boolean result = validator.validateAssignWish(wish1, wish2);

        Assertions.assertFalse(result);
    }

    @Test
    void validateAssignWish_shouldReturnFalse_whenDifferentShifts() {
        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user2, testDate, Shift.LATE);

        boolean result = validator.validateAssignWish(wish1, wish2);

        Assertions.assertFalse(result);
    }

    @Test
    void validateAssignWish_shouldThrow_whenUser1AlreadyScheduled() {
        ScheduleAssigned assignment = ScheduleAssigned.builder()
                .date(testDate)
                .shift(Shift.EARLY)
                .build();

        user1.setSchedule(List.of(assignment));

        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user2, testDate, Shift.EARLY);

        UserAlreadyHasShiftException ex = Assertions.assertThrows(
                UserAlreadyHasShiftException.class,
                () -> validator.validateAssignWish(wish1, wish2)
        );

        Assertions.assertTrue(ex.getMessage().contains("Alice"));
    }

    @Test
    void validateAssignWish_shouldThrow_whenUser2AlreadyScheduled() {
        ScheduleAssigned assignment = ScheduleAssigned.builder()
                .date(testDate)
                .shift(Shift.EARLY)
                .build();

        user2.setSchedule(List.of(assignment));

        ScheduleWish wish1 = createWish(user1, testDate, Shift.EARLY);
        ScheduleWish wish2 = createWish(user2, testDate, Shift.EARLY);

        UserAlreadyHasShiftException ex = Assertions.assertThrows(
                UserAlreadyHasShiftException.class,
                () -> validator.validateAssignWish(wish1, wish2)
        );

        Assertions.assertTrue(ex.getMessage().contains("Bob"));
    }
}
