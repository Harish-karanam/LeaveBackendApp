package com.example.demo.controller;



import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.RoleRequestDto;
import com.example.demo.dto.response.RoleResponseDto;

import com.example.demo.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponseDto createRole(
          @Valid  @RequestBody RoleRequestDto dto) {

        return roleService.createRole(dto);
    }

    @GetMapping
    public List<RoleResponseDto> getAllRoles() {

        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleResponseDto getRoleById(
            @PathVariable Long id) {

        return roleService.getRoleById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(
            @PathVariable Long id) {

        roleService.deleteRole(id);
    }
}