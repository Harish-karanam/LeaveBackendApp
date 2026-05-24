package com.example.demo.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRequestDto {

	@NotBlank(message = "employee code is required")
	@Size(min=2,max=20,message="employee code must be between 2 and 20 characters")
    private String employeeCode;

	@NotBlank(message = "first name is required")
	@Size(min=2,max=20,message="employee code must be between 2 and 20 characters")
    private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min=2,max=20,message="employee code must be between 2 and 20 characters")
    private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
    private String email;

	@NotBlank(message = "Password is required")
	@Size(min=2,max=20,message="employee code must be between 2 and 20 characters")
	@Pattern(
			   regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
		        message = "Password must contain uppercase, lowercase, and number"
		        )
	@NotBlank(message = "password is required")
    private String password;

	@NotNull(message = "RoleId is required")
    private Long roleId;

	@NotNull(message = "Manager id is required")
    private Long managerId;

	@NotNull(message = "departmentId is required")
    private Long departmentId;
	
	@NotNull(message = "ProjectId is required")
    private Long projectId;

	@NotNull(message = "Joining date is required")
	@FutureOrPresent(message = "joining date cannot be in past")
    private LocalDate joiningDate;
}