package ru.safeline.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safeline.api.dto.EmployeeDto;
import ru.safeline.api.response.ApiResponse;
import ru.safeline.api.service.EmployeeService;

import javax.validation.Valid;

import java.util.List;

import static ru.safeline.api.constant.SuccessMessages.EMPLOYEE_REMOVED;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "EmployeeController", description = "Содержит все CRUD операции связанные с сотрудниками")
public class EmployeeController {
    static final String EMPLOYEE_ID = "{id}";

    EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Создание сотрудника", description = "Позволяет создать 1 пользователя")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/array")
    @Operation(summary = "Создание сотрудников", description = "Позволяет создать пользователей")
    public ResponseEntity<List<EmployeeDto>> createEmployees(@RequestBody List<@Valid EmployeeDto> employees) {
        List<EmployeeDto> savedEmployees = employeeService.createEmployees(employees);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployees);
    }

    @GetMapping(EMPLOYEE_ID)
    @Operation(summary = "Получение сотрудника по id", description = "Позволяет получить информацию о сотруднике")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto foundEmployee = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(foundEmployee);
    }

    @GetMapping
    @Operation(summary = "Вывод списка сотрудников с фильтрацией", description = "Позволяет печать список пользователей с пагинацией")
    public ResponseEntity<Page<EmployeeDto>> getAllEmployees(
            @RequestParam(defaultValue = "0")
            @Parameter(description = "Управление страницами", example = "1") int page,
            @RequestParam(defaultValue = "10")
            @Parameter(description = "Количество выводимых пользователей на странице", example = "20") int size,
            @RequestParam(defaultValue = "id")
            @Parameter(description = "Параметр для сортировки", example = "id") String sortBy,
            @RequestParam(defaultValue = "asc")
            @Parameter(description = "Направление сортировки", example = "asc") String sortDir) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );

        Page<EmployeeDto> employees = employeeService.getAllEmployees(pageable);

        return ResponseEntity.ok(employees);
    }

    @PutMapping(EMPLOYEE_ID)
    @Operation(summary = "Обновление информации о сотруднике", description = "Позволяет обновить сотрудника")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @Valid @RequestBody EmployeeDto updateEmployee) {
         EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);

         return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping(EMPLOYEE_ID)
    @Operation(summary = "Удаление сотрудника", description = "Позволяет удалить сотрудника")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok(new ApiResponse(EMPLOYEE_REMOVED));
    }
}
