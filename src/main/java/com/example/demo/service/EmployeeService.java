package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);
    Employee updateEmployee(Long id, EmployeeDto employeeDto, boolean isManager, String managerDepartment);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByDepartment();
    List<Employee> searchEmployees(String name, Long id, Department department, String jobTitle);
    List<Employee> filterEmployees(String employmentStatus, Department department, LocalDate startDate, LocalDate endDate);
}
