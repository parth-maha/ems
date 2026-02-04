package com.roima.ems.controller;

import com.roima.ems.dto.EditEmployeeDTO;
import com.roima.ems.dto.EmployeeDTO;
import com.roima.ems.dto.UpdateEmployeeDTO;
import com.roima.ems.config.ApiResponse;
import com.roima.ems.service.EmployeeService;
import com.roima.ems.utils.Group1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee fetched.",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "/{id}", params = "version=2")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeIdByParameterVersion(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Parameter Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "/{id}",headers = "X-API-VERSION=3")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeIdByHeaderVersion(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Header Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "/{id}",produces = "application/vnd.roima.ems-v3+json")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeIdByContent(@PathVariable Long id){
        try{
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            ApiResponse<EmployeeDTO> response= new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Content Negotiation Versioning",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees(){
        try{
            List<EmployeeDTO> employees = employeeService.getEmployees();
            ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee Updated",
                    employees
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<List<EmployeeDTO>> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
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
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(@Valid @RequestBody EmployeeDTO dto){
        try{
            EmployeeDTO employee = employeeService.createEmployee(dto);
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee Created",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateEmployeeDTO>> updateEmployee(@PathVariable Long id, @Validated(Group1.class) @RequestBody UpdateEmployeeDTO dto){
        try{
            UpdateEmployeeDTO employee = employeeService.updateEmployee(id,dto);
            ApiResponse<UpdateEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee Updated",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<UpdateEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<EditEmployeeDTO>> editEmployee(@PathVariable Long id,@Valid @RequestBody EditEmployeeDTO dto){
        try{
            EditEmployeeDTO employee = employeeService.editEmployee(id,dto);
            ApiResponse<EditEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee Editted",
                    employee
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<EditEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateEmployeeDTO>> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            ApiResponse<UpdateEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Employee Deleted",
                    null
            );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            ApiResponse<UpdateEmployeeDTO> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
}
