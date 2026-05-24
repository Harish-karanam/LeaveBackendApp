package com.example.demo.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demo.dto.response.LeaveBalanceResponseDto;
import com.example.demo.entity.LeaveBalance;

@Mapper(componentModel = "spring")
public interface LeaveBalanceMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "leaveType.id", target = "leaveTypeId")
    @Mapping(source = "totalDays", target = "allocatedDays")
    LeaveBalanceResponseDto toResponseDto(LeaveBalance leaveBalance);
}