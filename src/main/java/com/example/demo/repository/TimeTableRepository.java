package com.example.demo.repository;

import com.example.demo.domain.TimeTable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeTableRepository extends JpaRepository<TimeTable,Integer> {
    List<TimeTable> findByTimeTableIndexBetween(Integer substring,Integer str2);
}
