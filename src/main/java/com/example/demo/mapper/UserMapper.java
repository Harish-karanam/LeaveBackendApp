package com.example.demo.mapper;




import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.request.UserRequestDto;

import com.example.demo.dto.response.UserResponseDto;

import com.example.demo.entity.User;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface UserMapper {

    User toEntity(
            UserRequestDto dto
    );

    @Mapping(
            source = "role.roleName",
            target = "roleName"
    )

    @Mapping(
            source = "manager.firstName",
            target = "managerName"
    )

    @Mapping(
            source = "department.departmentName",
            target = "departmentName"
    )

    @Mapping(
            source = "project.projectName",
            target = "projectName"
    )

    UserResponseDto toResponseDto(
            User user
    );
}