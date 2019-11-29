package com.example.demo.repository;

import com.example.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository  extends JpaRepository<Schedule,Integer> {
    List<Schedule> findByCalendarIdAndStartDateAfterAndEndDateBefore(Integer calendarId, String date, String date1);

    List<Schedule> findByCalendarId(Integer calendarId);
}
