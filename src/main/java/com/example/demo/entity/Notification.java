package com.example.demo.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import com.example.demo.enums.NotificationType;
import com.example.demo.enums.NotificationStatus;

@Entity
@Table(name="notifications")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String message;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Column(name="created_at")
    private LocalDateTime createdAt;
}
