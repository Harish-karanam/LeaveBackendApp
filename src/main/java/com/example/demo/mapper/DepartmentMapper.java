package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.dto.request.DepartmentRequestDto;
import com.example.demo.dto.response.DepartmentResponseDto;
import com.example.demo.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentRequestDto dto);

    DepartmentResponseDto toResponseDto(Department department);
}