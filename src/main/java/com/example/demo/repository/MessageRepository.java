package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findMessagesByToUserId(String userId);
}
