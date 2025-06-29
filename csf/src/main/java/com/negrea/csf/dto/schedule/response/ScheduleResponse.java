package com.negrea.csf.dto.schedule.response;

import com.negrea.csf.dto.schedule.ShiftDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    private ShiftDto earlyShift;
    private ShiftDto lateShift;
}
