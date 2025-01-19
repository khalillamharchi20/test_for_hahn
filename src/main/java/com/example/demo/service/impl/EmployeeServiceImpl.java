package com.example.demo.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFullName(employeeDto.getFullName());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setDepartment(Enum.valueOf(Department.class, employeeDto.getDepartment()));
        employee.setHireDate(employeeDto.getHireDate());
        employee.setEmploymentStatus(employeeDto.getEmploymentStatus());
        employee.setContactInformation(employeeDto.getContactInformation());
        employee.setAddress(employeeDto.getAddress());
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto, boolean isManager, String managerDepartment) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (isManager && !employee.getDepartment().toString().equals(managerDepartment)) {
            throw new RuntimeException("Unauthorized");
        }

        if (employeeDto.getFullName() != null) employee.setFullName(employeeDto.getFullName());
        if (employeeDto.getJobTitle() != null) employee.setJobTitle(employeeDto.getJobTitle());
        if (employeeDto.getContactInformation() != null) employee.setContactInformation(employeeDto.getContactInformation());
        if (employeeDto.getAddress() != null) employee.setAddress(employeeDto.getAddress());
        if (employeeDto.getDepartment() != null) employee.setDepartment(Enum.valueOf(Department.class, employeeDto.getDepartment()));

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByDepartment() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Unauthorized access");
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

       return employeeRepository.findByDepartment(Enum.valueOf(Department.class, user.getDepartment().name()));
    }

    
}
