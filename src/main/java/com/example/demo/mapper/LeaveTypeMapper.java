package com.example.demo.mapper;



import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.request.LeaveTypeRequestDto;

import com.example.demo.dto.response.LeaveTypeResponseDto;

import com.example.demo.entity.LeaveType;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface LeaveTypeMapper {

    LeaveType toEntity(
            LeaveTypeRequestDto dto
    );

    LeaveTypeResponseDto toResponseDto(
            LeaveType leaveType
    );
}