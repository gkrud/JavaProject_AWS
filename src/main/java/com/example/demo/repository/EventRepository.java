package com.example.demo.repository;


import com.example.demo.domain.Calendar;
import com.example.demo.domain.Event;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByEventStatusIs(boolean b);
}