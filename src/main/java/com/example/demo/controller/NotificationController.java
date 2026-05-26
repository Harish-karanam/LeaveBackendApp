package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.NotificationResponseDto;

import com.example.demo.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService
            notificationService;

    @GetMapping("/user/{userId}")
    public List<NotificationResponseDto>
    getUserNotifications(
            @PathVariable Long userId) {

        return notificationService
                .getUserNotifications(userId);
    }
}