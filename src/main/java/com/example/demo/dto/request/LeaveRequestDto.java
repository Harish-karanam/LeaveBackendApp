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


public class LeaveRequestDto {

    private Long userId;

    private Long leaveTypeId;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;
    private boolean halfDay;
}
