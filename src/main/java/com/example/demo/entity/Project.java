package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="projects")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="project_name")
    private String projectName;

    @Column(name="calendar_type")
    private String calendarType;

    @Column(name="weekend_config")
    private String weekendConfig;
}