package com.example.demo.controller;

import com.example.demo.domain.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private MessageRepository messageRepository;
    private EventService eventService;
    private CertifiedService certifiedService;

    public EventController() {
        this.eventService = new EventService();
        this.certifiedService = new CertifiedService();
    }
    @PostMapping(path = "/event/request")
    public ResponseEntity<String> uploadRequest(@RequestHeader Integer loginUserId,@RequestBody Event event, @RequestParam("uploadFile") MultipartFile uploadFile) {
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        eventService.requestEvent(event, eventRepository, messageRepository,loginUserRepository.findById(loginUserId),userRepository, uploadFile);
        return ResponseEntity.ok("Success");
    }
    @PutMapping(path = "/event/{eventId}")
    public ResponseEntity<String> addEventRequest(@RequestHeader Integer loginUserId, @PathVariable Integer eventId, @RequestBody Event event) {
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        eventService.eventStatus(eventId, eventRepository, event.getEventStatus());
        return ResponseEntity.ok("Success");
    }
    @GetMapping(value = "/event/{eventId}")
    public ResponseEntity<Event> readEvent(@RequestHeader Integer loginUserId, @PathVariable Integer eventId) {
        certifiedService.isLogin(loginUserId, loginUserRepository);
        return ResponseEntity.ok(eventService.readEvent(eventId, eventRepository));
    }
    @GetMapping(path = "/event")
    public ResponseEntity<List<Event>> adminEvent(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isAdmin(loginUserRepository.findById(loginUserId),userRepository);
        return ResponseEntity.ok(eventService.readAllEvent(eventRepository));
    }
}
