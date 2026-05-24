package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LeaveTypeRequestDto;
import com.example.demo.dto.response.LeaveTypeResponseDto;

import com.example.demo.entity.LeaveType;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.LeaveTypeMapper;

import com.example.demo.repository.LeaveTypeRepository;

@Service
@RequiredArgsConstructor
public class LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    private final LeaveTypeMapper leaveTypeMapper;

    public LeaveTypeResponseDto createLeaveType(
            LeaveTypeRequestDto dto) {

        LeaveType leaveType = leaveTypeMapper.toEntity(dto);

        LeaveType savedLeaveType =
                leaveTypeRepository.save(leaveType);

        return leaveTypeMapper.toResponseDto(savedLeaveType);
    }

    public List<LeaveTypeResponseDto> getAllLeaveTypes() {

        return leaveTypeRepository.findAll()
                .stream()
                .map(leaveTypeMapper::toResponseDto)
                .toList();
    }

    public LeaveTypeResponseDto getLeaveTypeById(Long id) {

        LeaveType leaveType = leaveTypeRepository.findById(id)
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Leave type not found"));

        return leaveTypeMapper.toResponseDto(leaveType);
    }

    public void deleteLeaveType(Long id) {

        leaveTypeRepository.deleteById(id);
    }
}