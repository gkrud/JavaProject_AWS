package com.example.demo.service;

import com.example.demo.domain.TimeTable;
import com.example.demo.repository.TimeTableRepository;

import java.util.List;
import java.util.Optional;

public class TimeTableService {
    public List<TimeTable> updateTimeTable(List<TimeTable> newTimeTable, TimeTableRepository timeTableRepository) {
        for(TimeTable timeTable : newTimeTable){
            Optional<TimeTable> table = timeTableRepository.findById(timeTable.getTimeTableIndex());
            if(table.isEmpty()){
                timeTableRepository.save(timeTable);
            }
            else{
                table.get().setSubject(timeTable.getSubject());
                table.get().setTeacher(timeTable.getTeacher());
            }
        }
        return timeTableRepository.findAll();
    }

    public List<TimeTable> readAllTimeTable(TimeTableRepository timeTableRepository) {
        return timeTableRepository.findAll();
    }

    public TimeTable createTimeTable(TimeTable timeTable, TimeTableRepository timeTableRepository) {
        return timeTableRepository.save(timeTable);
    }
}
