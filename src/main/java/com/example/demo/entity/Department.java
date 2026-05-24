package com.example.demo.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="departments")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_name")
    private String departmentName;
}
