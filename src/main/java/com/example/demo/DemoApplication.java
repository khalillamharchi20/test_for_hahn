package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.RoleName;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

import java.time.LocalDate;
import java.util.Set;
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

   

	@Bean
    CommandLineRunner initRolesAndUsers(RoleService roleService, UserService userService, EmployeeService employeeService) {
        return args -> {
           
            try {
                roleService.createRole(new RoleDto(RoleName.ROLE_ADMINISTRATOR));
                roleService.createRole(new RoleDto(RoleName.ROLE_HR));
                roleService.createRole(new RoleDto(RoleName.ROLE_MANAGER));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            
            try {
                userService.createUser(new UserDto("admin", "password", Set.of("ROLE_ADMINISTRATOR"), "IT"));
                userService.createUser(new UserDto("hr", "password", Set.of("ROLE_HR"), "IT"));
                userService.createUser(new UserDto("manager", "password", Set.of("ROLE_MANAGER"), "IT"));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            try {
            employeeService.createEmployee(new EmployeeDto(
                "Alice Johnson", "Software Engineer", "IT", 
                LocalDate.of(2020, 1, 15), "Full-Time", "alice.johnson@example.com", "123 Elm Street"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Bob Smith", "Marketing Specialist", "MARKETING", 
                LocalDate.of(2019, 3, 10), "Part-Time", "bob.smith@example.com", "456 Oak Avenue"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Charlie Brown", "Financial Analyst", "FINANCE", 
                LocalDate.of(2021, 6, 20), "Full-Time", "charlie.brown@example.com", "789 Pine Road"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Diana Prince", "Project Manager", "IT", 
                LocalDate.of(2018, 9, 1), "Full-Time", "diana.prince@example.com", "101 Maple Lane"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Ethan Hunt", "Marketing Manager", "MARKETING", 
                LocalDate.of(2017, 12, 5), "Full-Time", "ethan.hunt@example.com", "202 Birch Way"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Fiona Gallagher", "HR Specialist", "HR", 
                LocalDate.of(2022, 2, 15), "Full-Time", "fiona.gallagher@example.com", "303 Spruce Drive"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "George Bailey", "IT Support Specialist", "IT", 
                LocalDate.of(2020, 8, 25), "Part-Time", "george.bailey@example.com", "404 Cedar Lane"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Helen Parr", "Finance Manager", "FINANCE", 
                LocalDate.of(2016, 5, 30), "Full-Time", "helen.parr@example.com", "505 Chestnut Road"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Ian Fleming", "HR Manager", "HR", 
                LocalDate.of(2015, 11, 20), "Full-Time", "ian.fleming@example.com", "606 Walnut Street"
            ));
            employeeService.createEmployee(new EmployeeDto(
                "Jane Doe", "Data Analyst", "MARKETING", 
                LocalDate.of(2023, 4, 10), "Contract", "jane.doe@example.com", "707 Ash Avenue"
            ));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        };
    }

}
