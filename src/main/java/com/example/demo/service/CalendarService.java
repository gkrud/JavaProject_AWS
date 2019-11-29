package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CalendarService {
    public List<Schedule> readAllMyCalendar(Optional<LoginUser> loginUser, UserRepository userRepository, CalendarRepository calendarRepository, ScheduleRepository scheduleRepository) {
        Optional<Student> user = userRepository.findById(loginUser.get().getUserId());
        Optional<Calendar> calendar = calendarRepository.findById(user.get().getMyCalendarId());
        return scheduleRepository.findByCalendarId(calendar.get().getCalendarId());
    }

    public InSchoolCalendar readAllSchoolCalendar(CalendarRepository calendarRepository, ScheduleRepository scheduleRepository) {
        Optional<Calendar> calendar = calendarRepository.findByCategory("school");
        InSchoolCalendar inSchoolCalendar = new InSchoolCalendar();
        inSchoolCalendar.setSchoolCalendarId(calendar.get().getCalendarId());
        inSchoolCalendar.setScheduleList(scheduleRepository.findByCalendarId(calendar.get().getCalendarId()));
        return inSchoolCalendar;
    }

    public List<Schedule> deleteSchedule(Integer scheduleId, ScheduleRepository scheduleRepository) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        System.out.println("hello");
        scheduleRepository.delete(schedule.get());
        return scheduleRepository.findByCalendarId(schedule.get().getCalendarId());
    }

    public List<Schedule> updateSchedule(Integer scheduleId, ScheduleRepository scheduleRepository, Schedule newSchedule) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        schedule.get().setSchedule(newSchedule.getScheduleTitle(),schedule.get().getCalendarId(),newSchedule.getStartDate(),newSchedule.getEndDate(),newSchedule.getScheduleContent());
        scheduleRepository.save(schedule.get());
        return scheduleRepository.findByCalendarId(schedule.get().getCalendarId());
    }

    public List<Schedule> createSchedule(Schedule schedule, Integer calendarId, ScheduleRepository scheduleRepository) {
        schedule.setCalendarId(calendarId);
        scheduleRepository.save(schedule);
        return scheduleRepository.findByCalendarId(calendarId);
    }

    public Schedule readSchedule(Integer scheduleId, ScheduleRepository scheduleRepository) {
        return scheduleRepository.findById(scheduleId).get();
    }
}
