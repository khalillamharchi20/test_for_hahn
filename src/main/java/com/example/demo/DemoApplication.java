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
                
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            
        };
    }

}
