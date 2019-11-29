package com.example.demo.repository;

import com.example.demo.domain.Room;
import org.springframework.data.jdbc.repository.RowMapperMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Room findByCalendarId(Integer calendarId);

}
