package com.example.demo.service;



import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LeaveApprovalDto;
import com.example.demo.dto.request.LeaveRequestDto;
import com.example.demo.dto.response.LeaveResponseDto;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.LeaveType;
import com.example.demo.entity.User;

import com.example.demo.enums.LeaveStatus;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.LeaveRequestMapper;

import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.LeaveTypeRepository;
import com.example.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    private final LeaveTypeRepository leaveTypeRepository;

    private final UserRepository userRepository;

    private final LeaveRequestMapper leaveRequestMapper;

    public LeaveResponseDto applyLeave(
            LeaveRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "User not found"));

        LeaveType leaveType = leaveTypeRepository.findById(dto.getLeaveTypeId())
        		.orElseThrow(() ->
                new ResourceNotFoundException(
                        "Leave type not found"));

        LeaveRequest leaveRequest =
                leaveRequestMapper.toEntity(dto);

        leaveRequest.setUser(user);

        leaveRequest.setLeaveType(leaveType);

        double totalDays = ChronoUnit.DAYS.between(
                dto.getStartDate(),
                dto.getEndDate()) + 1;

        leaveRequest.setTotalDays(totalDays);

        leaveRequest.setStatus(LeaveStatus.PENDING);

        LeaveRequest savedLeave =
                leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponseDto(savedLeave);
    }

    public List<LeaveResponseDto> getAllLeaves() {

        return leaveRequestRepository.findAll()
                .stream()
                .map(leaveRequestMapper::toResponseDto)
                .toList();
    }

    public List<LeaveResponseDto> getLeavesByUserId(Long userId) {

        return leaveRequestRepository.findByUserId(userId)
                .stream()
                .map(leaveRequestMapper::toResponseDto)
                .toList();
    }

    public LeaveResponseDto approveLeave(
            Long leaveId,
            LeaveApprovalDto dto) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveId)
                .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Leave not found"));

        leaveRequest.setStatus(
        	    LeaveStatus.valueOf(dto.getStatus().toUpperCase())
        	);

        leaveRequest.setManagerComment(dto.getManagerComment());

        LeaveRequest updatedLeave =
                leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponseDto(updatedLeave);
    }
}