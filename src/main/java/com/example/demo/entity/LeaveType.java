package com.example.demo.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="leave_types")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="max_days")
    private Integer maxDays;

    @Column(name="carry_forward")
    private Boolean carryForward;

    @Column(name="requires_document")
    private Boolean requiresDocument;
}