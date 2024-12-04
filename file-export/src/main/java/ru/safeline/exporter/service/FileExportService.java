package ru.safeline.exporter.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.safeline.exporter.constant.UserMessages;
import ru.safeline.exporter.model.Employee;
import ru.safeline.exporter.mapper.EmployeeResponse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileExportService {

    final RestTemplate restTemplate;
    final Executor executor;

    @Value("${file-export.folder-name}")
    String exportFolderName;

    @Value("${file-export.api.employees-url}")
    String employeesApiUrl;

    public FileExportService(RestTemplate restTemplate, Executor executor) {
        this.restTemplate = restTemplate;
        this.executor = executor;
    }

    @Async
    public CompletableFuture<String> exportEmployeesToCsv() throws IOException {
        ResponseEntity<EmployeeResponse> response = restTemplate.getForEntity(employeesApiUrl, EmployeeResponse.class);

        List<Employee> employees = response.getBody().getContent();

        File csvFile = generateCsvFile(employees);

        return CompletableFuture.completedFuture(csvFile.getAbsolutePath());
    }

    public File generateCsvFile(List<Employee> employees) throws IOException {
        String userHome = System.getProperty("user.home");
        Path exportPath = Path.of(userHome, exportFolderName);

        if (!Files.exists(exportPath)) {
            Files.createDirectories(exportPath);
        }

        String fileName = generateFileName("employees");
        File file = new File(exportPath.toFile(), fileName);

        try (FileWriter writer = new FileWriter(file);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Role"))) {

            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getName(), employee.getRole());
            }
        } catch (IOException e) {
            throw new RuntimeException(UserMessages.CSV_GENERATING_ERROR, e);
        }

        return file;
    }

    private String generateFileName(String baseName) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        return baseName + "_" + date + ".csv";
    }
}
