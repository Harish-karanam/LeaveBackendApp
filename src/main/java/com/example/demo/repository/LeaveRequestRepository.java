package com.example.demo.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeaveRequest;
import com.example.demo.enums.LeaveStatus;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
	
	boolean existsByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	        Long userId,
	        LocalDate endDate,
	        LocalDate startDate
	);

    List<LeaveRequest> findByUserId(Long userId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findByUserIdAndStatus(Long userId,
                                             LeaveStatus status);
}