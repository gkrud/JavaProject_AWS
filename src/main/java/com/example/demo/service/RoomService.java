package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.exception.AlreadyUserException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.*;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RoomService {

    public List<Room> readAllRoom(Integer loginUserId, RoomRepository roomRepository, LoginUserRepository loginUserRepository, RoomMemberRepository roomMemberRepository) {
        List<RoomMember> roomMembers = roomMemberRepository.findByUserId(loginUserRepository.findById(loginUserId).get().getUserId());
        List<Room> rooms = new ArrayList<>();
        for(RoomMember roomMember:roomMembers){
            rooms.add(roomRepository.findById(roomMember.getRoomId()).get());
        }
        return rooms;
    }

    public List<Schedule> inRoom(Integer roomId, ScheduleRepository scheduleRepository, RoomRepository roomRepository) {
        List<Schedule> schedules = scheduleRepository.findByCalendarId(roomRepository.findById(roomId).get().getCalendarId());

        return schedules;
    }

    public String invitedMember(String sendUserId, String userId, Integer roomId, InvitedMemberRepository invitedMemberRepository, MessageRepository messageRepository, RoomRepository roomRepository) {
        if(!invitedMemberRepository.findByRoomIdAndUserId(roomId,userId).isEmpty()){
            throw new AlreadyUserException("user is already invited");
        }
        invitedMemberRepository.save(new InvitedMember(userId,roomId));

        Message message = messageRepository.save(new Message(true,userId,
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                roomId,
                0,
                2,
                roomRepository.findById(roomId).get().getRoomTitle()));
        System.out.println(message.getSendUserId());
        return "Ok";
    }

    public String deleteRoom(Integer roomId, RoomRepository roomRepository, RoomMemberRepository roomMemberRepository, CalendarRepository calendarRepository, ScheduleRepository scheduleRepository) {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isEmpty()){
            throw new NotFoundException("not found");
        }
        scheduleRepository.deleteAll(scheduleRepository.findByCalendarId(room.get().getCalendarId()));
        calendarRepository.delete(calendarRepository.findById(room.get().getCalendarId()).get());
        roomMemberRepository.deleteAll(roomMemberRepository.findByRoomId(room.get().getRoomId()));
        roomRepository.delete(room.get());

        return "Ok";
    }

    public List<RoomMember> updateMemberRight(Integer memberId, RoomMemberRepository roomMemberRepository, int memberRight) {
        Optional<RoomMember> roomMember = roomMemberRepository.findById(memberId);
        if(roomMember.isEmpty()){
            throw new NotFoundException("Not found");
        }
        roomMember.get().setMemberRight(memberRight);
        roomMemberRepository.save(roomMember.get());
        System.out.println(roomMember.get().getMemberRight());
        return roomMemberRepository.findByRoomId(roomMember.get().getRoomId());
    }

    public List<Room> createRoom(Integer loginUserId, String roomTitle, int iconIndex, LoginUserRepository loginUserRepository, RoomRepository roomRepository, CalendarRepository calendarRepository, RoomMemberRepository roomMemberRepository) {
        Calendar calendar = calendarRepository.save(new Calendar("group"));
        Room room = new Room(iconIndex,calendar.getCalendarId(),roomTitle);
        Room newRoom = roomRepository.save(room);
        roomMemberRepository.save(new RoomMember(loginUserRepository.findById(loginUserId).get().getUserId(),
                newRoom.getRoomId(),
                3));
        List<RoomMember> roomMembers = roomMemberRepository.findByUserId(loginUserRepository.findById(loginUserId).get().getUserId());
        List<Room> rooms = new ArrayList<>();
        for(RoomMember roomMember:roomMembers){
            Integer roomId = roomMember.getRoomId();
            if(roomRepository.findById(roomId).isEmpty())
                throw new NotFoundException("Not found");
            rooms.add(roomRepository.findById(roomId).get());
        }
        return rooms;
    }

    public String deleteRoomMember(RoomMember roomMember, RoomMemberRepository roomMemberRepository) {
        roomMemberRepository.delete(roomMember);
        return "ok";
    }

    public String updateRoomTitle(RoomRepository roomRepository, String roomTitle, Integer roomId) {
        Room room = roomRepository.findById(roomId).get();
        room.setRoomTitle(roomTitle);
        roomRepository.save(room);
        return "ok";
    }

    public List<RoomMember> roomMemberList(Integer roomId, RoomMemberRepository roomMemberRepository) {
        return roomMemberRepository.findByRoomId(roomId);
    }

}
