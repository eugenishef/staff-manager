package ru.safeline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safeline.api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
