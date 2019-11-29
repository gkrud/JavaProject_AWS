package com.example.demo.service;

import com.example.demo.domain.Event;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Message;
import com.example.demo.exception.FileIsEmptyException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventService {
    private final String UPLOADPATH = "C:\\Users\\user\\Desktop\\개인공부\\hackersCalendar\\src\\main\\resources\\static\\Image";

    public void requestEvent(Event newEvent, EventRepository eventRepository, MessageRepository messageRepository, Optional<LoginUser> loginUser, UserRepository userRepository, MultipartFile uploadFile) {
        if(uploadFile.isEmpty())
            throw new FileIsEmptyException("file is empty");
            String fileName = uploadFile.getOriginalFilename();
        try{
            byte[] data = uploadFile.getBytes();
            FileOutputStream fos = new FileOutputStream(UPLOADPATH+"/"+fileName);
            fos.write(data);
            fos.close();
            newEvent.setEventPoster("http://http://192.168.137.1:8080/static/"+fileName);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Event event = eventRepository.save(newEvent);
        messageRepository.save(new Message(false,
                userRepository.findByIsAdmin(true).getId(),
                new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                0,event.getEventId(),
                1,loginUser.get().getUserId()));
    }

    public void eventStatus(Integer eventId, EventRepository eventRepository, Boolean eventStatus) {
        Optional<Event> e = eventRepository.findById(eventId);
        if (eventStatus){
            e.get().setEventStatus(true);
            eventRepository.save(e.get());
        } else {
            eventRepository.delete(e.get());
        }
    }


    public Event readEvent(Integer eventId, EventRepository eventRepository) {
        Optional<Event> e = eventRepository.findById(eventId);
        if (!e.isPresent()){
            throw new NotFoundException("event not exists");
        }
        return e.get();
    }
    public List<Event> readAllEvent(EventRepository eventRepository){
        return eventRepository.findAll();
    }
}
