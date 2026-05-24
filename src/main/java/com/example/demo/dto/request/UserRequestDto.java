package com.example.demo.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDto {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long roleId;

    private Long managerId;

    private Long departmentId;

    private Long projectId;

    private LocalDate joiningDate;
}