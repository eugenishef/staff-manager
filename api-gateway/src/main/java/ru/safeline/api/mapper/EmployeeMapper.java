package ru.safeline.api.mapper;

import org.mapstruct.Mapper;
import ru.safeline.api.dto.EmployeeDto;
import ru.safeline.api.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);
}
