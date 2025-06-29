package com.negrea.csf.service;

import com.negrea.csf.dto.schedule.ShiftDto;
import com.negrea.csf.dto.schedule.response.ScheduleResponse;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.schedule.ScheduleAssigned;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.ScheduleAssignedRepository;
import com.negrea.csf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleAssignedRepository scheduleRepository;
    private final UserMapper userMapper;

    public ScheduleResponse getScheduleOfDay(LocalDate date) {
        //find employees scheduled for given day
        List<ScheduleAssigned> scheduleAssignedList = scheduleRepository.findByDate(date);

        //first 2 - EARLY / last 2 - LATE
        scheduleAssignedList.sort(Comparator.comparing(ScheduleAssigned::getShift));

        List<User> earlyEmployees = new ArrayList<User>();
        earlyEmployees.add(scheduleAssignedList.get(0).getUser());
        earlyEmployees.add(scheduleAssignedList.get(1).getUser());

        List<User> lateEmployees = new ArrayList<User>();
        lateEmployees.add(scheduleAssignedList.get(2).getUser());
        lateEmployees.add(scheduleAssignedList.get(3).getUser());

        //creating the DTOs for both shifts
        ShiftDto earlyShift = ShiftDto.builder()
                .employees(userMapper.toUserScheduleDtoList(earlyEmployees))
                .shift(scheduleAssignedList.get(0).getShift())
                .build();

        ShiftDto lateShift = ShiftDto.builder()
                .employees(userMapper.toUserScheduleDtoList(lateEmployees))
                .shift(scheduleAssignedList.get(0).getShift())
                .build();

        //creating final response with both shifts
        return ScheduleResponse.builder()
                .earlyShift(earlyShift)
                .lateShift(lateShift)
                .build();
    }
}
