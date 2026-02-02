package com.roima.ems.service;

import com.roima.ems.DTO.AuthResponseDTO;
import com.roima.ems.DTO.LoginDTO;
import com.roima.ems.entity.Employees;
import com.roima.ems.repository.EmployeeRepo;
import com.roima.ems.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepo employeeRepo;
    private final AuthUtil authUtil;

    public AuthResponseDTO login(LoginDTO dto) {
        Employees emp = employeeRepo.findByEmail(dto.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("Employee not found"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(emp.getEmail(), "");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = authUtil.generateAccessToken(emp);

        return new AuthResponseDTO(emp.getEmail(),token);
    }
}
