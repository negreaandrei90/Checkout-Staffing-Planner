package com.negrea.csf.dto.schedule.response;

import com.negrea.csf.dto.user.response.UserDtoResponse;
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
public class ScheduleWishDtoResponse {
    private Long id;
    private LocalDate date;
    private Shift shift;
    private UserDtoResponse user;
}
