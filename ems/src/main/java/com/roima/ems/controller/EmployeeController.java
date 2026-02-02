package com.roima.ems.controller;

import com.roima.ems.DTO.EditEmployeeDTO;
import com.roima.ems.DTO.EmployeeDTO;
import com.roima.ems.DTO.UpdateEmployeeDTO;
import com.roima.ems.config.ApiResponse;
import com.roima.ems.service.EmployeeService;
import com.roima.ems.utils.Group1;
import com.roima.ems.utils.Group2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try{
            EmployeeDTO response = employeeService.getEmployeeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}", params = "version=2")
    public ResponseEntity<?> getEmployeeIdByParameterVersion(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Parameter Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}",headers = "X-API-VERSION=3")
    public ResponseEntity<?> getEmployeeIdByHeaderVersion(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Header Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}",produces = "application/vnd.roima.ems-v3+json")
    public ResponseEntity<?> getEmployeeIdByContent(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Content Negotiation Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        try{
            List<EmployeeDTO> response = employeeService.getEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Create a new employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Payload"
                            )
                    )
            )
    )
    @PostMapping
    private ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO dto){
        try{
            EmployeeDTO response =employeeService.createEmployee(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add employee");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Validated(Group1.class) @RequestBody UpdateEmployeeDTO dto){
        try{
            UpdateEmployeeDTO response = employeeService.updateEmployee(id,dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable Long id,@Valid @RequestBody EditEmployeeDTO dto){
        try{
            EditEmployeeDTO response = employeeService.editEmployee(id,dto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
