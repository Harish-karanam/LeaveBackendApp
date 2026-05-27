package com.example.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LeaveApprovalDto;
import com.example.demo.dto.request.LeaveRequestDto;
import com.example.demo.dto.response.LeaveResponseDto;

import com.example.demo.entity.LeaveBalance;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.LeaveType;
import com.example.demo.entity.User;

import com.example.demo.enums.LeaveStatus;

import com.example.demo.exception.ResourceNotFoundException;

import com.example.demo.mapper.LeaveRequestMapper;

import com.example.demo.repository.HolidayCalendarRepository;
import com.example.demo.repository.LeaveBalanceRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.LeaveTypeRepository;
import com.example.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    private final LeaveTypeRepository leaveTypeRepository;

    private final UserRepository userRepository;

    private final LeaveBalanceRepository leaveBalanceRepository;

    private final HolidayCalendarRepository holidayCalendarRepository;

    private final LeaveRequestMapper leaveRequestMapper;
    
    private final AuditLogService auditLogService;

    public LeaveResponseDto applyLeave(
            LeaveRequestDto dto) {

        // FETCH USER

        User user = userRepository.findById(dto.getUserId())

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        // FETCH LEAVE TYPE

        LeaveType leaveType =
                leaveTypeRepository.findById(dto.getLeaveTypeId())

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Leave type not found"
                                )
                        );

        // VALIDATE DATES

        if(dto.getStartDate().isAfter(dto.getEndDate())) {

            throw new IllegalArgumentException(
                    "Start date cannot be after end date"
            );
        }

        // PREVENT PAST DATE LEAVE

        if(dto.getStartDate().isBefore(LocalDate.now())) {

            throw new IllegalArgumentException(
                    "Cannot apply leave for past dates"
            );
        }

        // CHECK OVERLAPPING LEAVES

        boolean alreadyApplied =

                leaveRequestRepository
                        .existsByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(

                                dto.getUserId(),

                                dto.getEndDate(),

                                dto.getStartDate()
                        );

        if(alreadyApplied) {

            throw new IllegalArgumentException(
                    "Leave already applied for selected dates"
            );
        }

        // CALCULATE LEAVE DAYS

        double totalDays = calculateLeaveDays(

                dto.getStartDate(),

                dto.getEndDate(),

                user.getProject().getId(),

                dto.isHalfDay()
        );

        // FETCH LEAVE BALANCE

        LeaveBalance leaveBalance =

                leaveBalanceRepository
                        .findByUserIdAndLeaveTypeIdAndYear(

                                user.getId(),

                                leaveType.getId(),

                                LocalDate.now().getYear()
                        )

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Leave balance not found"
                                )
                        );

        // CHECK BALANCE

        if(leaveBalance.getRemainingDays() < totalDays) {

            throw new IllegalArgumentException(
                    "Insufficient leave balance"
            );
        }

        // DTO -> ENTITY

        LeaveRequest leaveRequest =
                leaveRequestMapper.toEntity(dto);

        leaveRequest.setUser(user);

        leaveRequest.setLeaveType(leaveType);

        leaveRequest.setTotalDays(totalDays);

        leaveRequest.setStatus(LeaveStatus.PENDING);

        // SAVE

        LeaveRequest savedLeave =
                leaveRequestRepository.save(leaveRequest);
        
        auditLogService.createLog("leave_requests", savedLeave.getId(), "INSERT", user.getId());

        return leaveRequestMapper.toResponseDto(savedLeave);
    }

    public List<LeaveResponseDto> getAllLeaves() {

        return leaveRequestRepository.findAll()

                .stream()

                .map(leaveRequestMapper::toResponseDto)

                .toList();
    }

    public List<LeaveResponseDto> getLeavesByUserId(
            Long userId) {

        return leaveRequestRepository.findByUserId(userId)

                .stream()

                .map(leaveRequestMapper::toResponseDto)

                .toList();
    }
    
    //manageral approval
    public LeaveResponseDto managerApproveLeave(

            Long leaveId,

            LeaveApprovalDto dto) {

        LeaveRequest leaveRequest =

                leaveRequestRepository.findById(leaveId)

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Leave not found"
                                )
                        );

        if(leaveRequest.getStatus()
                != LeaveStatus.PENDING) {

            throw new IllegalArgumentException(
                    "Only pending leaves can be approved by manager"
            );
        }

        leaveRequest.setStatus(
                LeaveStatus.MANAGER_APPROVED
        );

        leaveRequest.setManagerComment(
                dto.getManagerComment()
        );

        LeaveRequest updatedLeave =
                leaveRequestRepository.save(leaveRequest);
        
        auditLogService.createLog("leave_requests", updatedLeave.getId(), "MANAGER_APPROVED", leaveRequest.getUser().getManager().getId());

        return leaveRequestMapper.toResponseDto(updatedLeave);
    }
    
    //hr approval 
    
    public LeaveResponseDto hrApproveLeave(

            Long leaveId,

            LeaveApprovalDto dto) {

        LeaveRequest leaveRequest =

                leaveRequestRepository.findById(leaveId)

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Leave not found"
                                )
                        );

        if(leaveRequest.getStatus()
                != LeaveStatus.MANAGER_APPROVED) {

            throw new IllegalArgumentException(
                    "Manager approval required first"
            );
        }

        leaveRequest.setStatus(
                LeaveStatus.HR_APPROVED
        );

        leaveRequest.setHrComment(
                dto.getManagerComment()
        );

        LeaveBalance leaveBalance =

                leaveBalanceRepository
                        .findByUserIdAndLeaveTypeIdAndYear(

                                leaveRequest.getUser().getId(),

                                leaveRequest.getLeaveType().getId(),

                                LocalDate.now().getYear()
                        )

                        .orElseThrow(() ->

                                new ResourceNotFoundException(
                                        "Leave balance not found"
                                )
                        );

        leaveBalance.setUsedDays(

                leaveBalance.getUsedDays()

                        + leaveRequest.getTotalDays()
        );

        leaveBalance.setRemainingDays(

                leaveBalance.getRemainingDays()

                        - leaveRequest.getTotalDays()
        );

        leaveBalanceRepository.save(leaveBalance);

        LeaveRequest updatedLeave =
                leaveRequestRepository.save(leaveRequest);
        auditLogService.createLog("leave_requests", updatedLeave.getId(), "HR_APPROVED", 2L);

        return leaveRequestMapper.toResponseDto(updatedLeave);
    }

//    public LeaveResponseDto approveLeave(
//
//            Long leaveId,
//
//            LeaveApprovalDto dto) {
//
//        LeaveRequest leaveRequest =
//
//                leaveRequestRepository.findById(leaveId)
//
//                        .orElseThrow(() ->
//
//                                new ResourceNotFoundException(
//                                        "Leave not found"
//                                )
//                        );
//
//        leaveRequest.setStatus(
//
//                LeaveStatus.valueOf(
//                        dto.getStatus().toUpperCase()
//                )
//        );
//
//        leaveRequest.setManagerComment(
//                dto.getManagerComment()
//        );
//
//        LeaveRequest updatedLeave =
//                leaveRequestRepository.save(leaveRequest);
//
//        return leaveRequestMapper.toResponseDto(updatedLeave);
//    }

    // PRIVATE METHOD

    private double calculateLeaveDays(

            LocalDate startDate,

            LocalDate endDate,

            Long projectId,

            boolean halfDay
    ) {

        List<LocalDate> holidays =

                holidayCalendarRepository
                        .findHolidayDates(projectId);

        double days = 0;

        for(LocalDate date = startDate;

            !date.isAfter(endDate);

            date = date.plusDays(1)) {

            DayOfWeek day =
                    date.getDayOfWeek();

            boolean weekend =

                    day == DayOfWeek.SATURDAY ||

                    day == DayOfWeek.SUNDAY;

            boolean holiday =
                    holidays.contains(date);

            if(!weekend && !holiday) {

                days++;
            }
        }

        if(halfDay) {

            return 0.5;
        }

        return days;
    }
}