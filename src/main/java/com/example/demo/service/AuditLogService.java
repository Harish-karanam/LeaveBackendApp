package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.AuditLogResponseDto;

import com.example.demo.mapper.AuditLogMapper;

import com.example.demo.repository.AuditLogRepository;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    private final AuditLogMapper auditLogMapper;

    public List<AuditLogResponseDto> getAllLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::toResponseDto)
                .toList();
    }
}