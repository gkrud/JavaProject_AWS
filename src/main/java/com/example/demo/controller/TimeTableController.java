package com.example.demo.controller;

import com.example.demo.domain.TimeTable;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableController {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private UserRepository userRepository;

    private TimeTableService timeTableService;
    private CertifiedService certifiedService;

    public TimeTableController() {
        this.timeTableService = new TimeTableService();
        this.certifiedService = new CertifiedService();
    }

    @PostMapping(value = "/timeTable")
    public ResponseEntity<List<TimeTable>> updateTimeTableRequest(@RequestHeader Integer loginUserId, @RequestBody List<TimeTable> timeTable){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);

        return ResponseEntity.ok(timeTableService.updateTimeTable(timeTable,timeTableRepository));
    }
    @GetMapping(value = "/timeTable")
    public ResponseEntity<List<TimeTable>> readAllTimeTableRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(timeTableService.readAllTimeTable(timeTableRepository));

    }
}
