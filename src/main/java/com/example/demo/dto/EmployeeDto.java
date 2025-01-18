package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private String fullName;
    private String jobTitle;
    private Department department;
    private LocalDate hireDate;
    private String employmentStatus;
    private String contactInformation;
    private String address;
}
