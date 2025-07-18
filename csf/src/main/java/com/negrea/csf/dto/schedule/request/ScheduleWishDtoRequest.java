package com.negrea.csf.dto.schedule.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull
    private Shift shift;
}
