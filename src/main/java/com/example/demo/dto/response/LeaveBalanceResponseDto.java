package com.example.demo.dto.response;

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


	

	
	public class LeaveBalanceResponseDto {

	    private Long id;

	    private Long userId;

	    private Long leaveTypeId;

	    private Double allocatedDays;

	    private Double usedDays;

	    private Double remainingDays;

	    private Integer year;
	}


