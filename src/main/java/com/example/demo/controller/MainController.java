package com.example.demo.controller;

import com.example.demo.domain.Event;
import com.example.demo.domain.InMain;
import com.example.demo.domain.Notice;
import com.example.demo.domain.TimeTable;
import com.example.demo.repository.*;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private EventRepository eventRepository;
    private MainService mainService;
    private CertifiedService certifiedService;
    public MainController() {
        this.mainService= new MainService();
        this.certifiedService = new CertifiedService();
    }
    @GetMapping(value = "/main")
    public ResponseEntity<InMain> mainRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);


        return ResponseEntity.ok(mainService.inMain(loginUserRepository.findById(loginUserId).get().getUserId(),eventRepository,noticeRepository,timeTableRepository,userRepository,scheduleRepository));
    }
}
