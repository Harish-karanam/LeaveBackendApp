package com.example.demo.controller;



import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LeaveTypeRequestDto;
import com.example.demo.dto.response.LeaveTypeResponseDto;

import com.example.demo.service.LeaveTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public LeaveTypeResponseDto createLeaveType(
          @Valid  @RequestBody LeaveTypeRequestDto dto) {

        return leaveTypeService.createLeaveType(dto);
    }

    @GetMapping
    public List<LeaveTypeResponseDto> getAllLeaveTypes() {

        return leaveTypeService.getAllLeaveTypes();
    }

    @GetMapping("/{id}")
    public LeaveTypeResponseDto getLeaveTypeById(
            @PathVariable Long id) {

        return leaveTypeService.getLeaveTypeById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLeaveType(
            @PathVariable Long id) {

        leaveTypeService.deleteLeaveType(id);
    }
}