package com.example.demo.dto.request;

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

public class LeaveTypeRequestDto {
	private String name;
	private Integer maxDays;
	private Boolean carryForward;
	private Boolean requiresDocument;

}
