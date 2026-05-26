package com.example.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.request.LeaveRequestDto;

import com.example.demo.dto.response.LeaveResponseDto;

import com.example.demo.entity.LeaveRequest;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface LeaveRequestMapper {

    LeaveRequest toEntity(
            LeaveRequestDto dto
    );

    @Mapping(
            source = "user.firstName",
            target = "employeeName"
    )

    @Mapping(
            source = "leaveType.name",
            target = "leaveType"
    )

    LeaveResponseDto toResponseDto(
            LeaveRequest leaveRequest
    );
}