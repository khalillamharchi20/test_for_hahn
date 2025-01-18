package com.example.demo.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String fullName;

    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private Department department;

    private LocalDate hireDate;

    private String employmentStatus;

    private String contactInformation;

    private String address;
    
}
