package com.roima.ems.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Authentication response")

public class AuthResponseDTO {

    @Schema(description = "Employee email" ,example = "example@eoimaint.com")
    private String email;

    @Schema(description = "JWT Token")
    private String token;
}
