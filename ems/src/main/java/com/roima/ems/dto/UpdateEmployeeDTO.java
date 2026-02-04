package com.roima.ems.dto;

import com.roima.ems.utils.Group1;
import com.roima.ems.utils.Group2;
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
@Schema(description = "DTO for PUT request")
public class UpdateEmployeeDTO {

    @Schema(description = "Employee name" ,example = "Parth")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email
    @NotNull(message = "Email cannot be null")
    @Schema(description = "Employee email" ,example = "example@eoimaint.com")
    private String email;

    @Schema(description = "Department name" ,example = "Lean")
    @Email(groups = Group2.class,message = "Department should be a email")
    @NotNull(message = "Department cannot be null",groups = Group1.class)
    private String department;

    @Min(0)
    @Schema(description = "Salary per month" ,example = "50000")
    @NotNull(message = "Salary cannot be null")
    private Integer salary;

    private LocalDateTime createdAt;

    private LocalDateTime modified_at;
}
