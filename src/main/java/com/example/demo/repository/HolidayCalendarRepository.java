package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.example.demo.entity.HolidayCalendar;
import com.example.demo.enums.HolidayType;

public interface HolidayCalendarRepository
        extends JpaRepository<HolidayCalendar, Long> {

    boolean existsByHolidayDate(
            LocalDate holidayDate
    );

    List<HolidayCalendar> findByTypeOrProjectId(

            HolidayType type,

            Long projectId
    );

    List<HolidayCalendar> findByHolidayDateBetween(

            LocalDate startDate,

            LocalDate endDate
    );

    // ADD THIS METHOD

    @Query("""

            SELECT h.holidayDate

            FROM HolidayCalendar h

            WHERE

            h.type = 'ORG'

            OR

            (h.type = 'PROJECT'
            AND h.project.id = :projectId)

            """)

    List<LocalDate> findHolidayDates(

            @Param("projectId")
            Long projectId
    );
}