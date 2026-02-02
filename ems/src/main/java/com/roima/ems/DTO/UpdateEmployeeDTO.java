package com.roima.ems.DTO;

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
public class UpdateEmployeeDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @Email
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Department cannot be null")
    private String department;

    @Min(0)
    @NotNull(message = "Salary cannot be null")
    private Integer salary;

    private LocalDateTime createdAt;

    private LocalDateTime modified_at;
}
