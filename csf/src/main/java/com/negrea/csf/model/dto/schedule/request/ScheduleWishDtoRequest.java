package com.negrea.csf.model.dto.schedule.request;

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
public class ScheduleWishDtoRequest {
    @NotNull
    private LocalDate localDate;
    @NotNull
    private Shift shift;
    @NotNull
    private Long userId;
}
