package com.negrea.csf.model.dto.user.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDtoResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    private String description;
}
