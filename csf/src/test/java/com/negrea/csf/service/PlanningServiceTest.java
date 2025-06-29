package com.negrea.csf.service;

import com.negrea.csf.config.security.CurrentUser;
import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.dto.user.response.UserScheduleDto;
import com.negrea.csf.exception.InvalidWishException;
import com.negrea.csf.exception.WishNotFoundException;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.ScheduleWish;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleWishRepository;
import com.negrea.csf.repository.UserRepository;
import com.negrea.csf.utils.validator.schedule.ScheduleValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
public class PlanningServiceTest {

    @Mock
    private ScheduleWishRepository wishRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ScheduleValidator validator;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private PlanningService planningService;

    private User user1, user2;
    private ScheduleWish wish1, wish2;
    private ScheduleAssigned assigned1, assigned2;
    private Shift shift;
    private LocalDate date;

    @BeforeEach
    void setup() {
        user1 = new User();
        user1.setId(1L);
        user1.setSchedule(new ArrayList<>());

        user2 = new User();
        user2.setId(2L);
        user2.setSchedule(new ArrayList<>());

        shift = Shift.EARLY;
        date = LocalDate.of(2025, 8, 20);

        wish1 = ScheduleWish.builder().id(1L).user(user1).date(date).shift(shift).build();
        wish2 = ScheduleWish.builder().id(2L).user(user2).date(date).shift(shift).build();

        assigned1 = ScheduleAssigned.builder().date(date).shift(shift).user(user1).build();
        assigned2 = ScheduleAssigned.builder().date(date).shift(shift).user(user2).build();
    }

    @Test
    void assignWish_shouldAssignWishes_whenValid() {
        // Arrange
        Mockito.when(wishRepository.findById(1L)).thenReturn(Optional.of(wish1));
        Mockito.when(wishRepository.findById(2L)).thenReturn(Optional.of(wish2));
        Mockito.when(validator.validateAssignWish(wish1, wish2)).thenReturn(true);
        Mockito.when(currentUser.getCurrentUser()).thenReturn(new User()); // mocked assigner

        Mockito.when(userMapper.toUserScheduleDtoList(anyList()))
                .thenReturn(List.of(new UserScheduleDto(), new UserScheduleDto()));

        // Act
        ShiftDto result = planningService.assignWish(1L, 2L);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(date, result.getDate());
        Assertions.assertEquals(shift, result.getShift());
        Mockito.verify(userRepository).save(user1);
        Mockito.verify(userRepository).save(user2);
    }

    @Test
    void assignWish_shouldThrow_whenWishNotFound() {
        // Arrange
        Mockito.when(wishRepository.findById(1L)).thenReturn(Optional.empty());

        // Arrange
        WishNotFoundException ex = Assertions.assertThrows(
                WishNotFoundException.class,
                () -> planningService.assignWish(1L, 2L)
        );

        Assertions.assertEquals("Could not find the wishes", ex.getMessage());
    }

    @Test
    void assignWish_shouldThrow_whenValidationFails() {
        // Arrange
        Mockito.when(wishRepository.findById(1L)).thenReturn(Optional.of(wish1));
        Mockito.when(wishRepository.findById(2L)).thenReturn(Optional.of(wish2));
        Mockito.when(validator.validateAssignWish(wish1, wish2)).thenReturn(false);

        // Assert
        InvalidWishException ex = Assertions.assertThrows(
                InvalidWishException.class,
                () -> planningService.assignWish(1L, 2L)
        );

        Assertions.assertEquals("The wishes cannot be planned together", ex.getMessage());
    }
}
