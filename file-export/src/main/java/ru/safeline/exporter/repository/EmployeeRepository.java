package ru.safeline.exporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safeline.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
