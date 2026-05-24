package com.example.demo.mapper;



import org.mapstruct.Mapper;

import com.example.demo.dto.request.RoleRequestDto;
import com.example.demo.dto.response.RoleResponseDto;
import com.example.demo.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleRequestDto dto);

    RoleResponseDto toResponseDto(Role role);
}