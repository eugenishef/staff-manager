package ru.safeline.exporter.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.safeline.exporter.constant.LogMessages;
import ru.safeline.exporter.constant.UserMessages;
import ru.safeline.exporter.repository.EmployeeRepository;
import ru.safeline.exporter.service.FileExportService;
import ru.safeline.models.Employee;

import java.io.File;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/export")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class FileExportController {
    final FileExportService fileExportService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity<String> exportEmployeesToCsv() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            File file = fileExportService.generateCsvFile(employees);

            return ResponseEntity.ok(UserMessages.START_EXPORT + file.getAbsolutePath());
        } catch (Exception e) {
            log.error(LogMessages.EXPORT_ERROR, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(UserMessages.TRY_EXPORT_LATER);
        }
    }
}



