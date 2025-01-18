package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Department;

import lombok.Data;

@Data
public class EmployeeDto {
    private String fullName;
    private String employeeId;
    private String jobTitle;
    private Department department;
    private LocalDate hireDate;
    private String employmentStatus;
    private String contactInformation;
    private String address;
}
