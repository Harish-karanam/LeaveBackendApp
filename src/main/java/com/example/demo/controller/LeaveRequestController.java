package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LeaveApprovalDto;
import com.example.demo.dto.request.LeaveRequestDto;

import com.example.demo.dto.response.LeaveResponseDto;

import com.example.demo.service.LeaveRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public LeaveResponseDto applyLeave(
       @Valid     @RequestBody LeaveRequestDto dto) {

        return leaveRequestService.applyLeave(dto);
    }

    @GetMapping
    public List<LeaveResponseDto> getAllLeaves() {

        return leaveRequestService.getAllLeaves();
    }

    @GetMapping("/user/{userId}")
    public List<LeaveResponseDto>
    getLeavesByUserId(
            @PathVariable Long userId) {

        return leaveRequestService
                .getLeavesByUserId(userId);
    }
    

    @PutMapping("/{leaveId}/manager-approve")

    public LeaveResponseDto managerApproveLeave(

            @PathVariable Long leaveId,

            @Valid
            @RequestBody LeaveApprovalDto dto) {

        return leaveRequestService
                .managerApproveLeave(leaveId, dto);
    }
    
    @PutMapping("/{leaveId}/hr-approve")

    public LeaveResponseDto hrApproveLeave(

            @PathVariable Long leaveId,

            @Valid
            @RequestBody LeaveApprovalDto dto) {

        return leaveRequestService
                .hrApproveLeave(leaveId, dto);
    }

//    @PutMapping("/{leaveId}/approve")
//    public LeaveResponseDto approveLeave(
//            @PathVariable Long leaveId,
//          @Valid  @RequestBody LeaveApprovalDto dto) {
//
//        return leaveRequestService
//                .approveLeave(leaveId, dto);
//    }
}