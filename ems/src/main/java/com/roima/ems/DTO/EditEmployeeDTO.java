package com.roima.ems.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for put request")
public class EditEmployeeDTO {

    @Schema(description = "Employee name" ,example = "Parth")
    private String name;

    @Email
    @Schema(description = "Employee email" ,example = "example@eoimaint.com")
    private String email;

    @Schema(description = "Department name" ,example = "Lean")
    private String department;

    @Min(0)
    @Schema(description = "Salary per month" ,example = "50000")
    private Integer salary;

    private LocalDateTime createdAt;

    private LocalDateTime modified_at;
}
