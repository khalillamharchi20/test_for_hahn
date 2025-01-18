package com.example.demo.dto;


import com.example.demo.entity.RoleName;

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
public class RoleDto {
    private RoleName roleName;
}