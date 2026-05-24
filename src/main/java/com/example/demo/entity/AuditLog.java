package com.example.demo.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="audit_logs")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="entity_name")
    private String entityName;

    @Column(name="entity_id")
    private Long entityId;

    private String action;

    @ManyToOne
    @JoinColumn(name="performed_by")
    private User performedBy;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}