package com.roima.ems.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Employee DTO")
public class EmployeeDTO {

    @Schema(description = "Employee id" ,example = "1")
    private Long id;

    @Schema(description = "Employee name" ,example = "Parth")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email
    @Schema(description = "Employee email" ,example = "example@eoimaint.com")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Schema(description = "Department name" ,example = "Lean")
    @NotNull(message = "Department cannot be null")
    private String department;

    @Schema(description = "Salary per month" ,example = "50000")
    @Min(0)
    @NotNull(message = "Salary cannot be null")
    private Integer salary;

    private LocalDateTime createdAt;

    private LocalDateTime modified_at;
}
