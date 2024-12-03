package ru.safeline.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.safeline.api.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> createEmployees(List<EmployeeDto> employees);
    EmployeeDto getEmployeeById(Long employeeDto);
    Page<EmployeeDto> getAllEmployees(Pageable pageable);
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
    void deleteEmployee(Long employeeId);
}
