package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.HolidayCalendarRequestDto;
import com.example.demo.dto.response.HolidayCalendarResponseDto;
import com.example.demo.entity.HolidayCalendar;

@Mapper(componentModel = "spring")
public interface HolidayCalendarMapper {

    HolidayCalendar toEntity(HolidayCalendarRequestDto dto);

    HolidayCalendarResponseDto toResponseDto(HolidayCalendar holidayCalendar);
}