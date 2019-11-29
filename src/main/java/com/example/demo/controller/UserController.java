package com.example.demo.controller;

import com.example.demo.domain.Calendar;
import com.example.demo.domain.Student;
import com.example.demo.domain.UserInfo;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private CalendarRepository calendarRepository;

    private UserService userService;
    private CertifiedService certifiedService;
    public UserController() {
        this.userService = new UserService();
        this.certifiedService = new CertifiedService();
    }

    private static Logger logger = LoggerFactory.getLogger("UserController");

    @RequestMapping(method = RequestMethod.POST, path = "/join")
    public ResponseEntity<String> joinRequest(@RequestBody Student student){
        logger.info("joinRequest");
        userService.join(student ,userRepository,calendarRepository);
        return ResponseEntity.ok("OK");
    }
    @PostMapping(value = "/admin")
    public ResponseEntity<String> createAdmin(@RequestBody Student student){
        Calendar calendar = calendarRepository.save(new Calendar("student"));
        student.setMyCalendarId(calendar.getCalendarId());
        student.setClassOf(1100);
        student.setAdmin(true);
        userRepository.save(student);
        return ResponseEntity.ok("ok");
    }
    @PostMapping(value = "/loginUser")
    public ResponseEntity<UserInfo> loginUserRequest(@RequestBody Student student){
        return ResponseEntity.ok(userService.loginUser(student, userRepository,loginUserRepository,loginUserRepository));
    }
    @GetMapping(value = "/logout")
    public ResponseEntity<String> logoutRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(userService.logout(loginUserId,loginUserRepository));
    }
}
