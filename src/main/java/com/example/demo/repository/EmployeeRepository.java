package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);
}
