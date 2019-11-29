package com.example.demo.repository;

import com.example.demo.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar,Integer> {
    Optional<Calendar> findByCategory(String category);
}
