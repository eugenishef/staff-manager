package ru.safeline.exporter.mapper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import ru.safeline.models.Employee;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    List<Employee> content;
    Pageable pageable;
    boolean last;
    int totalPages;
    int totalElements;
    int number;
    boolean first;
    int size;
    int numberOfElements;
    boolean empty;
}

