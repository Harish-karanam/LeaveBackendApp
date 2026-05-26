package com.example.demo.controller;



import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.LeaveBalanceResponseDto;

import com.example.demo.service.LeaveBalanceService;

@RestController
@RequestMapping("/api/leave-balances")
@RequiredArgsConstructor
public class LeaveBalanceController {

    private final LeaveBalanceService leaveBalanceService;

    @GetMapping
    public List<LeaveBalanceResponseDto>
    getAllBalances() {

        return leaveBalanceService.getAllBalances();
    }

    @GetMapping("/{id}")
    public LeaveBalanceResponseDto getBalanceById(
            @PathVariable Long id) {

        return leaveBalanceService.getBalanceById(id);
    }
}