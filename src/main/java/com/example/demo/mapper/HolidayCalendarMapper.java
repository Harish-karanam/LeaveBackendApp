package com.example.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.request.HolidayCalendarRequestDto;

import com.example.demo.dto.response.HolidayCalendarResponseDto;

import com.example.demo.entity.HolidayCalendar;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface HolidayCalendarMapper {

    HolidayCalendar toEntity(
            HolidayCalendarRequestDto dto
    );

    @Mapping(
            source = "project.id",
            target = "projectId"
    )

    HolidayCalendarResponseDto toResponseDto(
            HolidayCalendar holidayCalendar
    );
}