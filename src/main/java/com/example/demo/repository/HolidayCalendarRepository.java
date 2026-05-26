package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.HolidayCalendar;

public interface HolidayCalendarRepository extends JpaRepository<HolidayCalendar, Long>{

    boolean existsByHolidayDate(LocalDate holidayDate);

    List<HolidayCalendar>
    findByHolidayDateBetween(
            LocalDate startDate,
            LocalDate endDate);
}