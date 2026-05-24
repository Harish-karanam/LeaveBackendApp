package com.example.demo.entity;


 
import java.time.*;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import com.example.demo.enums.LeaveStatus;

@Entity
@Table(name="leave_requests")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="leave_type_id")
    private LeaveType leaveType;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="total_days")
    private Double totalDays;

    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column(name="manager_comment")
    private String managerComment;

    @Column(name="hr_comment")
    private String hrComment;

    @Column(name="applied_at")
    private LocalDateTime appliedAt;
}