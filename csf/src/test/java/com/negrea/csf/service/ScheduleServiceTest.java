package com.negrea.csf.service;

import com.negrea.csf.dto.schedule.response.ScheduleResponse;
import com.negrea.csf.dto.user.response.UserScheduleDto;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleAssignedRepository;
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

import static org.mockito.ArgumentMatchers.anyList;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {
    @Mock
    private ScheduleAssignedRepository scheduleRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ScheduleService scheduleService;

    private LocalDate testDate;
    private User user1, user2;
    private ScheduleAssigned early1, early2, late1, late2;

    @BeforeEach
    void setup() {
        testDate = LocalDate.of(2025, 7, 1);

        user1 = new User();
        user1.setId(1L);
        user1.setSchedule(new ArrayList<>());

        user2 = new User();
        user2.setId(2L);
        user2.setSchedule(new ArrayList<>());

        early1 = ScheduleAssigned.builder().date(testDate).shift(Shift.EARLY).user(user1).build();
        early2 = ScheduleAssigned.builder().date(testDate).shift(Shift.EARLY).user(user2).build();

        late1 = ScheduleAssigned.builder().date(testDate).shift(Shift.LATE).user(user1).build();
        late2 = ScheduleAssigned.builder().date(testDate).shift(Shift.LATE).user(user2).build();
    }

    @Test
    void getScheduleOfDay_shouldReturnShifts_whenSchedulesExist() {
        // Arrange
        List<ScheduleAssigned> scheduleList = List.of(early1, early2, late1, late2);
        Mockito.when(scheduleRepository.findByDate(testDate)).thenReturn(scheduleList);
        Mockito.when(userMapper.toUserScheduleDtoList(anyList()))
                .thenReturn(List.of(new UserScheduleDto(), new UserScheduleDto()));

        // Act
        ScheduleResponse result = scheduleService.getScheduleOfDay(testDate);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getEarlyShift());
        Assertions.assertNotNull(result.getLateShift());
        Assertions.assertEquals(testDate, result.getEarlyShift().getDate());
        Assertions.assertEquals(Shift.EARLY, result.getEarlyShift().getShift());
        Assertions.assertEquals(Shift.LATE, result.getLateShift().getShift());
    }

    @Test
    void getScheduleOfDay_shouldThrow_whenNoSchedulesFound() {
        // Arrange
        Mockito.when(scheduleRepository.findByDate(testDate)).thenReturn(List.of());

        // Assert
        RuntimeException ex = Assertions.assertThrows(
                RuntimeException.class,
                () -> scheduleService.getScheduleOfDay(testDate)
        );

        Assertions.assertEquals("Nobody is schedule for " + testDate, ex.getMessage());
    }

    @Test
    void getScheduleOfDay_shouldReturnPartialShifts_whenOnlyOneShiftPresent() {
        // Arrange
        Mockito.when(scheduleRepository.findByDate(testDate)).thenReturn(List.of(early1, early2));
        Mockito.when(userMapper.toUserScheduleDtoList(anyList()))
                .thenReturn(List.of(new UserScheduleDto(), new UserScheduleDto()));

        // Act
        ScheduleResponse result = scheduleService.getScheduleOfDay(testDate);

        // Assert
        Assertions.assertNotNull(result.getEarlyShift());
        Assertions.assertNull(result.getLateShift());
    }
}
