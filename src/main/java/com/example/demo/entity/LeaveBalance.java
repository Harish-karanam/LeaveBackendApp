package com.example.demo.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="leave_balance")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="leave_type_id")
    private LeaveType leaveType;

    @Column(name="total_days")
    private Double totalDays;

    @Column(name="used_days")
    private Double usedDays;

    @Column(name="remaining_days")
    private Double remainingDays;

    private Integer year;
}