package com.example.demo.dto.request;




import java.time.LocalDateTime;

import com.example.demo.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class LeaveApprovalDto {

	@NotBlank(message = "status is required")
    private String status;

	@Size(max=500, message = "manager comment cannot exceed 500 characters")
    private String managerComment;
}