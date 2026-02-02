package com.roima.ems.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for login")
public class LoginDTO {

    @Schema(description = "Employee email" ,example = "example@eoimaint.com")
    @Email(message = "Email is not valid")
    private String email;
}
