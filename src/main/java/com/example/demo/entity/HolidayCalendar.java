package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="holiday_calendar")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HolidayCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="holiday_name")
    private String holidayName;

    @Column(name="holiday_date")
    private LocalDate holidayDate;

    private String type;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
}