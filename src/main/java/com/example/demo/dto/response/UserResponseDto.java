package com.example.demo.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.User;
import com.example.demo.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponseDto {

    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String roleName;

    private String managerName;

    private String departmentName;

    private String projectName;

    private UserStatus status;

    private LocalDate joiningDate;
}