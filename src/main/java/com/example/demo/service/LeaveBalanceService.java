package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.LeaveBalanceResponseDto;

import com.example.demo.mapper.LeaveBalanceMapper;

import com.example.demo.repository.LeaveBalanceRepository;

@Service
@RequiredArgsConstructor
public class LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;

    private final LeaveBalanceMapper leaveBalanceMapper;

    public List<LeaveBalanceResponseDto> getAllBalances() {

        return leaveBalanceRepository.findAll()
                .stream()
                .map(leaveBalanceMapper::toResponseDto)
                .toList();
    }

    public LeaveBalanceResponseDto getBalanceById(Long id) {

        return leaveBalanceRepository.findById(id)
                .map(leaveBalanceMapper::toResponseDto)
                .orElseThrow(() ->
                        new RuntimeException("Balance not found"));
    }
}