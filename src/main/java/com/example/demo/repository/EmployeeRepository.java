package com.example.demo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);

     @Query("SELECT e FROM Employee e WHERE " +
           "(:name IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:id IS NULL OR e.id = :id) AND " +
           "(:department IS NULL OR e.department = :department) AND " +
           "(:jobTitle IS NULL OR LOWER(e.jobTitle) LIKE LOWER(CONCAT('%', :jobTitle, '%')))")
    List<Employee> searchEmployees(@Param("name") String name,
                                    @Param("id") Long id,
                                    @Param("department") Department department,
                                    @Param("jobTitle") String jobTitle);

    
    @Query("SELECT e FROM Employee e WHERE " +
           "(:status IS NULL OR e.employmentStatus = :status) AND " +
           "(:department IS NULL OR e.department = :department) AND " +
           "(:startDate IS NULL OR e.hireDate >= :startDate) AND " +
           "(:endDate IS NULL OR e.hireDate <= :endDate)")
    List<Employee> filterEmployees(@Param("status") String employmentStatus,
                                   @Param("department") Department department,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);
}
