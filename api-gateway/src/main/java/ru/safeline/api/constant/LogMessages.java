package ru.safeline.api.constant;

public class LogMessages {
    private LogMessages() {}

    public static final String EMPLOYEE_CREATED = "Employee created successfully: {}";
    public static final String EMPLOYEE_SAVED = "Employee created successfully with ID: {}";
    public static final String MULTIPLE_EMPLOYEE_CREATED = "Creating multiple employees: {}";
    public static final String MULTIPLE_EMPLOYEE_SAVED = "Successfully created {} employees.";
    public static final String EMPLOYEE_UPDATING = "Updating employee with ID: {}";
    public static final String EMPLOYEE_UPDATED = "Employee updated successfully: {}";
    public static final String EMPLOYEE_DELETING = "Deleting employee with ID: {}";
    public static final String EMPLOYEE_DELETED = "Employee deleted successfully: {}";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found with ID: {}";
    public static final String EMPLOYEE_LIST_FETCHED = "Fetched list of employees: {}";
    public static final String EMPLOYEE_FETCHED = "Fetched employee details with ID: {}";
    public static final String EMPLOYEE_FETCHED_SUCCESSFULLY = "Employee fetched successfully: {}";
    public static final String EMPLOYEE_FETCHED_ALL = "Fetching all employees with pagination: {}";
    public static final String EMPLOYEE_SAVE_FAILED = "Failed to save employee: {}";
    public static final String VALIDATION_SPACES = "Validation failed: Employee name contains spaces - {}";
    public static final String VALIDATION_EMPTY_NAME = "Validation failed: Employee name is empty.";
}
