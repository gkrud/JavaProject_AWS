package com.example.demo.service;

import com.example.demo.domain.InvitedMember;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Message;
import com.example.demo.domain.RoomMember;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.InvitedMemberRepository;
import com.example.demo.repository.LoginUserRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RoomMemberRepository;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MessageService {
    public List<Message> readAllMessage(LoginUser loginUser, MessageRepository messageRepository) {
        return messageRepository.findMessagesByToUserId(loginUser.getUserId());
    }

    public List<Message> deleteMessage(Integer messageId, MessageRepository messageRepository, Integer loginUserId, LoginUserRepository loginUserRepository) {
        messageRepository.delete(messageRepository.findById(messageId).get());
        return messageRepository.findMessagesByToUserId(loginUserRepository.findById(loginUserId).get().getUserId());
    }

    public String responseInvite(Integer messageId, MessageRepository messageRepository, RoomMemberRepository roomMemberRepository, InvitedMemberRepository invitedMemberRepository, LoginUser loginUser, boolean status) {
        Message message = messageRepository.findById(messageId).get();
        if(status==true){
            roomMemberRepository.save(new RoomMember(message.getToUserId(),message.getRoomId(),1));
        }
        Optional<InvitedMember> invitedMembers = invitedMemberRepository.findByRoomIdAndUserId(message.getRoomId(),message.getToUserId());
        invitedMemberRepository.delete(invitedMembers.get());
        messageRepository.delete(message);
        return "Ok";
    }
}
