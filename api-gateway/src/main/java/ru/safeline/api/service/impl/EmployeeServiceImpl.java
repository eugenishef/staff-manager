package ru.safeline.api.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.safeline.api.constant.LogMessages;
import ru.safeline.api.dto.EmployeeDto;
import ru.safeline.api.exception.ResourceNotFoundException;
import ru.safeline.api.constant.ErrorMessages;
import ru.safeline.api.mapper.EmployeeMapper;
import ru.safeline.api.model.Employee;
import ru.safeline.api.repository.EmployeeRepository;
import ru.safeline.api.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        log.info(LogMessages.EMPLOYEE_CREATED, employeeDto);
        Employee employee = employeeMapper.toEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        log.info(LogMessages.EMPLOYEE_SAVED, savedEmployee.getId());

        return employeeMapper.toEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> createEmployees(List<EmployeeDto> employees) {
        log.info(LogMessages.MULTIPLE_EMPLOYEE_CREATED, employees);
        for (EmployeeDto employee : employees) {
            if (employee.getName().contains(" ")) {
                log.error(LogMessages.VALIDATION_SPACES, employee.getName());
                throw new IllegalArgumentException(String.format(ErrorMessages.NAME_CONTAINS_SPACES, employee.getName()));
            }
            if (employee.getName().isEmpty()) {
                log.error(LogMessages.VALIDATION_EMPTY_NAME);
                throw new IllegalArgumentException(ErrorMessages.NAME_IS_EMPTY);
            }
        }

        List<Employee> employeeEntities = employees.stream()
                .map(employeeMapper::toEmployee)
                .collect(Collectors.toList());

        List<Employee> savedEmployees = employeeRepository.saveAll(employeeEntities);
        log.info(LogMessages.MULTIPLE_EMPLOYEE_SAVED, savedEmployees.size());

        return savedEmployees.stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.info(LogMessages.EMPLOYEE_FETCHED, employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.EMPLOYEE_NOT_FOUND, employeeId)
                ));

        log.info(LogMessages.EMPLOYEE_FETCHED_SUCCESSFULLY, employee);
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
        log.info(LogMessages.EMPLOYEE_FETCHED_ALL, pageable);
        return employeeRepository.findAll(pageable)
                .map(employeeMapper::toEmployeeDto);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto refreshEmployeeData) {
        log.info(LogMessages.EMPLOYEE_UPDATING, employeeId);
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ErrorMessages.EMPLOYEE_NOT_FOUND, employeeId)
        ));

        String previousName = employee.getName();
        String previousRole = employee.getRole();

        employee.setName(refreshEmployeeData.getName());
        employee.setRole(refreshEmployeeData.getRole());

        Employee updatedEmployee = employeeRepository.save(employee);

        log.info(LogMessages.EMPLOYEE_UPDATED,
                updatedEmployee.getId(), previousName, previousRole,
                updatedEmployee.getName(), updatedEmployee.getRole());

        return employeeMapper.toEmployeeDto(updatedEmployee);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        log.info(LogMessages.EMPLOYEE_DELETING, employeeId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.EMPLOYEE_NOT_FOUND, employeeId)
                ));

        employeeRepository.deleteById(employeeId);
        log.info(LogMessages.EMPLOYEE_DELETED, employeeId);
    }
}
