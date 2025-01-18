package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);
    Employee updateEmployee(Long id, EmployeeDto employeeDto, boolean isManager, String managerDepartment);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByDepartment(String department);
}
