package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.ProjectRequestDto;
import com.example.demo.dto.response.ProjectResponseDto;

import com.example.demo.entity.Project;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProjectMapper;

import com.example.demo.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectResponseDto createProject(ProjectRequestDto dto) {

        Project project = projectMapper.toEntity(dto);

        Project savedProject = projectRepository.save(project);

        return projectMapper.toResponseDto(savedProject);
    }

    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toResponseDto)
                .toList();
    }

    public ProjectResponseDto getProjectById(Long id) {

        Project project = projectRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Project not found"));

        return projectMapper.toResponseDto(project);
    }

    public void deleteProject(Long id) {

        projectRepository.deleteById(id);
    }
}