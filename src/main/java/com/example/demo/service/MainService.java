package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainService {
    public InMain inMain(String userId, EventRepository eventRepository, NoticeRepository noticeRepository, TimeTableRepository timeTableRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        Student student = userRepository.findById(userId).get();
        String str = Integer.toString(student.getClassOf()).substring(0,2);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<Schedule> schedules = scheduleRepository.findByCalendarIdAndStartDateAfterAndEndDateBefore(student.getMyCalendarId(),date,date);
        System.out.println(schedules);
        List<TimeTable> timetable = timeTableRepository.findByTimeTableIndexBetween(Integer.parseInt(str)*100,Integer.parseInt(str)*100+99);
        List<Notice> notices = noticeRepository.findAll();
        List<Event> events = eventRepository.findByEventStatusIs(true);

        return new InMain(events,notices,timetable,schedules);
    }
}
