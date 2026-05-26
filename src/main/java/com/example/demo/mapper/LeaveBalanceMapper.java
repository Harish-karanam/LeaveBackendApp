package com.example.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.response.LeaveBalanceResponseDto;

import com.example.demo.entity.LeaveBalance;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface LeaveBalanceMapper {

    @Mapping(
            source = "user.id",
            target = "userId"
    )

    @Mapping(
            source = "leaveType.id",
            target = "leaveTypeId"
    )

    LeaveBalanceResponseDto toResponseDto(
            LeaveBalance leaveBalance
    );
}