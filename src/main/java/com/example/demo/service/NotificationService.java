package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.NotificationResponseDto;

import com.example.demo.mapper.NotificationMapper;

import com.example.demo.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper;

    public List<NotificationResponseDto> getUserNotifications(
            Long userId) {

        return notificationRepository.findByUserId(userId)
                .stream()
                .map(notificationMapper::toResponseDto)
                .toList();
    }
}