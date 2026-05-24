package com.example.demo.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeaveBalance;

@Repository
public interface LeaveBalanceRepository
        extends JpaRepository<LeaveBalance, Long> {

    Optional<LeaveBalance> findByUserIdAndLeaveTypeIdAndYear(
            Long userId,
            Long leaveTypeId,
            Integer year
    );
}
