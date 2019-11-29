package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.domain.MessageStatus;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private InvitedMemberRepository invitedMemberRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMemberRepository roomMemberRepository;

    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    private MessageService messageService;
    private CertifiedService certifiedService;

    public MessageController() {
        this.messageService = new MessageService();
        this.certifiedService = new CertifiedService();
    }
    @GetMapping(value = "/message")
    public ResponseEntity<List<Message>> readAllMessageRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(messageService.readAllMessage(loginUserRepository.findById(loginUserId).get(),messageRepository));
    }
    @DeleteMapping(value = "/message/{messageId}")
    public ResponseEntity<List<Message>> deleteMessageRequest(@RequestHeader Integer loginUserId, @PathVariable Integer messageId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isMessageUser(loginUserId,loginUserRepository,messageRepository,messageId);
        if(messageRepository.findById(messageId).isEmpty()){
            throw new NotFoundException("Not found");
        }
        return ResponseEntity.ok(messageService.deleteMessage(messageId,messageRepository,loginUserId,loginUserRepository));
    }

    @PostMapping(value = "/message/{messageId}")
    public ResponseEntity<String> responseInviteRequest(@RequestHeader Integer loginUserId, @PathVariable Integer messageId, @RequestBody MessageStatus messageStatus){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isMessageUser(loginUserId,loginUserRepository,messageRepository,messageId);
        if(messageRepository.findById(messageId).isEmpty()){
            throw new NotFoundException("Not found");
        }
        return ResponseEntity.ok(messageService.responseInvite(messageId,messageRepository,roomMemberRepository,invitedMemberRepository,loginUserRepository.findById(loginUserId).get(),messageStatus.isStatus()));
    }
}
