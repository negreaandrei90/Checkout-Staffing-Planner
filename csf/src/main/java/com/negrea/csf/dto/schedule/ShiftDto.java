package com.negrea.csf.dto.schedule;

import com.negrea.csf.dto.user.response.UserScheduleDto;
import com.negrea.csf.model.schedule.Shift;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDto {
    private List<UserScheduleDto> employees;
    private Shift shift;
}
