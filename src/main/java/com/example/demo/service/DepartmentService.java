package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.DepartmentRequestDto;
import com.example.demo.dto.response.DepartmentResponseDto;

import com.example.demo.entity.Department;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DepartmentMapper;

import com.example.demo.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public DepartmentResponseDto createDepartment(
            DepartmentRequestDto dto) {

        Department department =
                departmentMapper.toEntity(dto);

        Department savedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toResponseDto(savedDepartment);
    }

    public List<DepartmentResponseDto> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();
    }

    public DepartmentResponseDto getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Department not found"));

        return departmentMapper.toResponseDto(department);
    }

    public void deleteDepartment(Long id) {

        departmentRepository.deleteById(id);
    }
}