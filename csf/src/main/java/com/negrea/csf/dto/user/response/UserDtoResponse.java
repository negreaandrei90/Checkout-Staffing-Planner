package com.negrea.csf.dto.user.response;

import com.negrea.csf.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private RoleDtoResponse role;
}
