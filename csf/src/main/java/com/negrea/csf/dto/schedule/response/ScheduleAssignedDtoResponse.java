package com.negrea.csf.dto.schedule.response;

import com.negrea.csf.model.schedule.Shift;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAssignedDtoResponse {
    private Long id;
    private LocalDate localDate;
    private Shift shift;
    private Long userId;
    private Long assignedByUserId;
}
