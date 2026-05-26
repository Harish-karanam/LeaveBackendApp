package com.example.demo.mapper;


import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.request.ProjectRequestDto;

import com.example.demo.dto.response.ProjectResponseDto;

import com.example.demo.entity.Project;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface ProjectMapper {

    Project toEntity(
            ProjectRequestDto dto
    );

    ProjectResponseDto toResponseDto(
            Project project
    );
}