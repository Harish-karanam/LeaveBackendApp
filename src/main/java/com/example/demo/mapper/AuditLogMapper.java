package com.example.demo.mapper;




import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.ReportingPolicy;

import com.example.demo.dto.response.AuditLogResponseDto;

import com.example.demo.entity.AuditLog;

@Mapper(

        componentModel = "spring",

        unmappedTargetPolicy =
                ReportingPolicy.IGNORE
)
public interface AuditLogMapper {

    @Mapping(
            source = "performedBy.id",
            target = "performedBy"
    )

    AuditLogResponseDto toResponseDto(
            AuditLog auditLog
    );
}


