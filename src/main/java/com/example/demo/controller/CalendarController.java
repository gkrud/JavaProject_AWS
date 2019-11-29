package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.exception.SchoolCalendarIsExistsException;
import com.example.demo.repository.*;
import com.example.demo.service.CalendarService;
import com.example.demo.service.CertifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CalendarController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMemberRepository roomMemberRepository;

    private CalendarService calendarService;
    private CertifiedService certifiedService;

    public CalendarController() {
        this.calendarService = new CalendarService();
        this.certifiedService = new CertifiedService();
    }

    @GetMapping(value = "/myCalendar")
    public ResponseEntity<List<Schedule>> readAllMyCalendarRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);

        return ResponseEntity.ok(calendarService.readAllMyCalendar(loginUserRepository.findById(loginUserId),userRepository,calendarRepository,scheduleRepository));
    }
    @GetMapping(value = "/createSchool")
    public ResponseEntity<String> createSchoolCalendar(){
        calendarRepository.save(new Calendar("school"));
        return ResponseEntity.ok("ok");
    }
    @GetMapping(value = "/schoolCalendar")
    public ResponseEntity<InSchoolCalendar> readAllSchoolCalendarRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(calendarService.readAllSchoolCalendar(calendarRepository,scheduleRepository));
    }
    @PutMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<List<Schedule>> updateScheduleRequest(@RequestHeader Integer loginUserId, @RequestBody Schedule schedule, @PathVariable Integer scheduleId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        if(calendarRepository.findById(scheduleRepository.findById(scheduleId).get().getCalendarId()).get().getCategory()=="school"){
            certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        }
        Calendar calendar = calendarRepository.findById(scheduleRepository.findById(scheduleId).get().getCalendarId()).get();
        if(calendar.getCategory()=="group"){
            Optional<RoomMember> roomMember = certifiedService.isMember(loginUserRepository.findById(loginUserId),calendar.getCalendarId(),roomMemberRepository,roomRepository);
            certifiedService.isHaveMemberRight(roomMember);
        }
        return ResponseEntity.ok(calendarService.updateSchedule(scheduleId,scheduleRepository,schedule));
    }
    @DeleteMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<List<Schedule>> deleteScheduleRequest(@RequestHeader Integer loginUserId, @PathVariable Integer scheduleId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        Calendar calendar = calendarRepository.findById(schedule.getCalendarId()).get();
        if(calendar.getCategory()=="school"){
            certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        }
        if(calendar.getCategory()=="group"){
            Optional<RoomMember> roomMember = certifiedService.isMember(loginUserRepository.findById(loginUserId),calendar.getCalendarId(),roomMemberRepository,roomRepository);
            certifiedService.isHaveMemberRight(roomMember);
        }
        return ResponseEntity.ok(calendarService.deleteSchedule(scheduleId,scheduleRepository));
    }
    @PostMapping(value = "/schedule/{calendarId}")
    public ResponseEntity<List<Schedule>> createScheduleRequest(@RequestHeader Integer loginUserId, @RequestBody Schedule schedule, @PathVariable Integer calendarId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        if(calendarRepository.findById(calendarId).get().getCategory().equals("school")){
            certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        }
        Calendar calendar = calendarRepository.findById(calendarId).get();
        if(calendar.getCategory().equals("group")){
            Optional<RoomMember> roomMember = certifiedService.isMember(loginUserRepository.findById(loginUserId),calendar.getCalendarId(),roomMemberRepository,roomRepository);
            certifiedService.isHaveMemberRight(roomMember);
        }
        return ResponseEntity.ok(calendarService.createSchedule(schedule,calendarId,scheduleRepository));
    }
    @GetMapping(value = "/schedule/{scheduleId}")
    public ResponseEntity<Schedule> readScheduleRequest(@RequestHeader Integer loginUserId, @PathVariable Integer scheduleId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Calendar isCalendar = calendarRepository.findById(scheduleRepository.findById(scheduleId).get().getCalendarId()).get();
        if(isCalendar.getCategory().equals("student")){
            certifiedService.isHaveCalendar(loginUserRepository.findById(loginUserId).get().getUserId(),userRepository,isCalendar);
        }
        else if(isCalendar.getCategory().equals("group")){
            certifiedService.isMember(loginUserRepository.findById(loginUserId),isCalendar.getCalendarId(),roomMemberRepository,roomRepository);
        }
        return ResponseEntity.ok(calendarService.readSchedule(scheduleId,scheduleRepository));
    }
}