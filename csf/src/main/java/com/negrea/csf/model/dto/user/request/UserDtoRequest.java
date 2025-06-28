package com.negrea.csf.model.dto.user.request;

import com.negrea.csf.model.user.Role;
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
public class UserDtoRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private Long roleId;
}
