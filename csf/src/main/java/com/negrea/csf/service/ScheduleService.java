package com.negrea.csf.service;

import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.dto.schedule.response.ScheduleResponse;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.schedule.Shift;
import com.negrea.csf.repository.ScheduleAssignedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleAssignedRepository scheduleRepository;
    private final UserMapper userMapper;

    public ScheduleResponse getScheduleOfDay(LocalDate date) {
        //find schedules of employees for given day
        List<ScheduleAssigned> scheduleAssignedList = scheduleRepository.findByDate(date);

        //list not empty = nobody is scheduled for the date
        if(scheduleAssignedList.isEmpty()) {
            return ScheduleResponse.builder()
                    .earlyShift(null)
                    .lateShift(null)
                    .build();
        }

        ShiftDto earlyShift = createShift(scheduleAssignedList, Shift.EARLY);
        ShiftDto lateShift = createShift(scheduleAssignedList, Shift.LATE);

        //creating final response with both shifts
        return ScheduleResponse.builder()
                .earlyShift(earlyShift)
                .lateShift(lateShift)
                .build();
    }

    /*
    Create DTO per shift (EARLY/LATE), after filtering the day's schedules depending on shift type
     */
    private ShiftDto createShift(List<ScheduleAssigned> scheduleAssignedList, Shift shift) {
        List<ScheduleAssigned> shiftSchedule = scheduleAssignedList.stream()
                .filter(schedule -> schedule.getShift().equals(shift))
                .toList();

        if(!shiftSchedule.isEmpty()) {
            return ShiftDto.builder()
                    .employees(userMapper.toUserScheduleDtoList(Arrays.asList(shiftSchedule.get(0).getUser(), shiftSchedule.get(1).getUser())))
                    .date(shiftSchedule.get(0).getDate())
                    .shift(shift)
                    .build();
        } else {
            return null;
        }
    }
}
