package com.roima.ems.service;

import com.roima.ems.dto.EditEmployeeDTO;
import com.roima.ems.dto.EmployeeDTO;
import com.roima.ems.dto.UpdateEmployeeDTO;
import com.roima.ems.entity.Employees;
import com.roima.ems.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    @Value("${image.path}")
    String imageFolder;

    public List<EmployeeDTO> getEmployees() {
        List<Employees> employees = employeeRepo.findAll();
        return employees.stream()
                .map(e -> modelMapper.map(e,EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO getEmployeeById(Long id){
        Employees employee = employeeRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));

        return modelMapper.map(employee,EmployeeDTO.class);
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        if(employeeRepo.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Employee already exists");
        }

        Employees employees = new Employees();
        employees = modelMapper.map(dto,Employees.class);
        employeeRepo.save(employees);

        return modelMapper.map(employees,EmployeeDTO.class);
    }

    // PUT REQUEST
    @Transactional
    public UpdateEmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO dto){
        Employees employee = employeeRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dto.getDepartment());

        employee = employeeRepo.save(employee);
        return modelMapper.map(employee,UpdateEmployeeDTO.class);
    }

    // PATCH REQUEST

    public void deleteEmployee(Long id){
        Employees employee = employeeRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));

        employeeRepo.deleteById(id);
    }

    public EditEmployeeDTO editEmployee(Long id, EditEmployeeDTO dto) {
        Employees employee = employeeRepo.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));

        modelMapper.map(dto,employee);

        return modelMapper.map(employeeRepo.save(employee), EditEmployeeDTO.class);
    }
}
