package com.example.demo.service;



import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;

import com.example.demo.entity.*;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;

import com.example.demo.repository.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final DepartmentRepository departmentRepository;

    private final ProjectRepository projectRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(UserRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
        	throw new DuplicateResourceException(
        	        "Email already exists");
        }

        if (userRepository.existsByEmployeeCode(dto.getEmployeeCode())) {
        	throw new DuplicateResourceException(
        	        "Email already exists");
        }

        User user = userMapper.toEntity(dto);

        Role role = roleRepository.findById(dto.getRoleId())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Role not found"));

        Department department = departmentRepository.findById(dto.getDepartmentId())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Department not found"));

        Project project = projectRepository.findById(dto.getProjectId())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Project not found"));

        user.setRole(role);
        user.setDepartment(department);
        user.setProject(project);

        if (dto.getManagerId() != null) {

            User manager = userRepository.findById(dto.getManagerId())
            		.orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Manager not found"));

            user.setManager(manager);
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"));

        return userMapper.toResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }
}