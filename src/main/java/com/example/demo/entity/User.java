package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import com.example.demo.enums.UserStatus;
@Entity
@Table(name="users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_code", unique = true)
    private String employeeCode;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private User manager;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    
    private LocalDate joiningDate;
}
