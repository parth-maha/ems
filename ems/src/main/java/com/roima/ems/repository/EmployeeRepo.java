package com.roima.ems.repository;

import com.roima.ems.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees,Long> {
    Optional<Employees> findByEmail(String email);
}
