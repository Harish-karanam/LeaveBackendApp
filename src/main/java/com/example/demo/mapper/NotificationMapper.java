package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.response.NotificationResponseDto;
import com.example.demo.entity.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponseDto toResponseDto(Notification notification);
}