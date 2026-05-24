package com.example.demo.dto.response;

import java.time.LocalDateTime;

import com.example.demo.entity.User;
import com.example.demo.enums.NotificationStatus;
import com.example.demo.enums.NotificationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NotificationResponseDto {
	private Long id;

	private String title;

	private String message;

	private NotificationType type;

	private NotificationStatus status;

	private LocalDateTime createdAt;

}
