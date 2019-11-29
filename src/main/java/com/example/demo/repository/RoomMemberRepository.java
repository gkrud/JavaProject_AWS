package com.example.demo.repository;

import com.example.demo.domain.RoomMember;
import com.example.demo.domain.Student;
import org.springframework.data.jdbc.repository.RowMapperMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Integer> {

    Optional<RoomMember> findByRoomIdAndUserId(Integer roomId, String userId);

    List<RoomMember> findByUserId(String userId);

    List<RoomMember> findByRoomId(Integer roomId);

    RoomMember findByUserIdAndRoomId(String roomMemberId, Integer roomId);
}
