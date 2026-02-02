package com.roima.ems.DTO;

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
public class EditEmployeeDTO {

    private String name;

    @Email
    private String email;

    private String department;

    @Min(0)
    private Integer salary;

    private LocalDateTime createdAt;

    private LocalDateTime modified_at;
}
