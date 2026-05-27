package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.response.AuditLogResponseDto;
import com.example.demo.entity.AuditLog;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AuditLogMapper;

import com.example.demo.repository.AuditLogRepository;
import com.example.demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    private final AuditLogMapper auditLogMapper;
    
    private final UserRepository userRepository;
    
    public void createLog(

            String entityName,

            Long entityId,

            String action,

            Long performedBy) {

        AuditLog auditLog = new AuditLog();

        auditLog.setEntityName(entityName);

        auditLog.setEntityId(entityId);

        auditLog.setAction(action);

//        auditLog.setPerformedBy(performedBy);
        User user = userRepository.findById(performedBy)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        auditLog.setPerformedBy(user);
        		

        auditLogRepository.save(auditLog);
    }

    public List<AuditLogResponseDto> getAllLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::toResponseDto)
                .toList();
    }
}