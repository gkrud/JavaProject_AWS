package com.example.demo.controller;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.CertifiedService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomMemberRepository roomMemberRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private InvitedMemberRepository invitedMemberRepository;
    @Autowired
    private MessageRepository messageRepository;

    private RoomService roomService;
    private CertifiedService certifiedService;

    public RoomController() {
        this.roomService = new RoomService();
        this.certifiedService = new CertifiedService();
    }
    @PostMapping(value = "/room")
    public ResponseEntity<List<Room>> createRoomRequest(@RequestHeader Integer loginUserId, @RequestBody Room room){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(roomService.createRoom(loginUserId,room.getRoomTitle(),room.getIconIndex(),loginUserRepository,roomRepository,calendarRepository,roomMemberRepository));
    }
    @GetMapping(value = "/room")
    public ResponseEntity<List<Room>> readAllRoomRequest(@RequestHeader Integer loginUserId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        return ResponseEntity.ok(roomService.readAllRoom(loginUserId,roomRepository,loginUserRepository,roomMemberRepository));
    }
    @PostMapping(value = "/room/{roomId}")
    public ResponseEntity<String> invitedMemberRequest(@RequestHeader Integer loginUserId, @RequestBody Student user, @PathVariable Integer roomId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Optional<RoomMember> roomMember = certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        certifiedService.isRoomAdmin(roomMember.get().getMemberRight());
        return ResponseEntity.ok(roomService.invitedMember(loginUserRepository.findById(loginUserId).get().getUserId(),user.getId(),roomId,invitedMemberRepository,messageRepository,roomRepository));
    }
    @GetMapping(value = "/room/{roomId}")
    public ResponseEntity<List<Schedule>> roomScheduleRequest(@RequestHeader Integer loginUserId, @PathVariable Integer roomId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        return ResponseEntity.ok(roomService.inRoom(roomId,scheduleRepository,roomRepository));
    }
    @GetMapping(value = "/room/roomMember/{roomId}")
    public ResponseEntity<List<RoomMember>> roomMemberListRequest(@RequestHeader Integer loginUserId, @PathVariable Integer roomId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        return ResponseEntity.ok(roomService.roomMemberList(roomId,roomMemberRepository));
    }
    @DeleteMapping(value = "/room/{roomId}")
    public ResponseEntity<String> deleteRoomRequest(@RequestHeader Integer loginUserId, @PathVariable Integer roomId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Optional<RoomMember> roomMember = certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        certifiedService.isRoomAdmin(roomMember.get().getMemberRight());
        return ResponseEntity.ok(roomService.deleteRoom(roomId,roomRepository,roomMemberRepository,calendarRepository,scheduleRepository));
    }
    @PutMapping(value = "/room/roomMember/{memberId}")
    public ResponseEntity<List<RoomMember>> updateMemberRightRequest(@RequestHeader Integer loginUserId,@PathVariable Integer memberId, @RequestBody RoomMember roomMember){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Optional<RoomMember> member = certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomMemberRepository.findById(memberId).get().getRoomId()).get().getCalendarId(),roomMemberRepository,roomRepository);
        certifiedService.isRoomAdmin(member.get().getMemberRight());
        return ResponseEntity.ok(roomService.updateMemberRight(memberId,roomMemberRepository,roomMember.getMemberRight()));
    }
    @DeleteMapping(value = "/room/roomMember/{roomId}")
    public ResponseEntity<String> deleteRoomMemberRequest(@RequestHeader Integer loginUserId, @PathVariable Integer roomId, @RequestParam Integer memberId){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        certifiedService.isRoomAdmin(roomMemberRepository.findById(memberId).get().getMemberRight());
        return ResponseEntity.ok(roomService.deleteRoomMember(roomMemberRepository.findById(memberId).get(),roomMemberRepository));
    }
    @PutMapping(value = "/room/{roomId}")
    public ResponseEntity<String> updateRoomTitleRequestTitle(@RequestHeader Integer loginUserId, @PathVariable Integer roomId, @RequestBody String roomTitle){
        certifiedService.isLogin(loginUserId,loginUserRepository);
        Optional<RoomMember> member = certifiedService.isMember(loginUserRepository.findById(loginUserId),roomRepository.findById(roomId).get().getCalendarId(),roomMemberRepository,roomRepository);
        certifiedService.isRoomAdmin(member.get().getMemberRight());
        return ResponseEntity.ok(roomService.updateRoomTitle(roomRepository,roomTitle,roomId));
    }
}
