package com.example.demo.mapper;


import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.response.NotificationResponseDto;

import com.example.demo.entity.Notification;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface NotificationMapper {

    NotificationResponseDto toResponseDto(
            Notification notification
    );
}