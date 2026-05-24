package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.RoleRequestDto;
import com.example.demo.dto.response.RoleResponseDto;

import com.example.demo.entity.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.RoleMapper;

import com.example.demo.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleResponseDto createRole(RoleRequestDto dto) {

        Role role = roleMapper.toEntity(dto);

        Role savedRole = roleRepository.save(role);

        return roleMapper.toResponseDto(savedRole);
    }

    public List<RoleResponseDto> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponseDto)
                .toList();
    }

    public RoleResponseDto getRoleById(Long id) {

        Role role = roleRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Role not found"));

        return roleMapper.toResponseDto(role);
    }

    public void deleteRole(Long id) {

        roleRepository.deleteById(id);
    }
}