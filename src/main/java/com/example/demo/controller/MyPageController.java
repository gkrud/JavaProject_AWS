package com.example.demo.controller;

import com.example.demo.domain.Student;
import com.example.demo.repository.*;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyPageController {
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

    private MyPageService myPageService;
    private CertifiedService certifiedService;

    public MyPageController() {
        this.myPageService = new MyPageService();
        this.certifiedService = new CertifiedService();
    }

    @GetMapping(value = "/myPage")
    public ResponseEntity<Student> myPageRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(userRepository.findById(loginUserRepository.findById(loginUserId).get().getUserId()).get());
    }
    @PutMapping(value = "/myPage")
    public ResponseEntity<Student> updateMyPageRequest(@RequestHeader Integer loginUserId, @RequestBody Student student){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(myPageService.updateIndex(student,userRepository,loginUserRepository.findById(loginUserId)));
    }
}
