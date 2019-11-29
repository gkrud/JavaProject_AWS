package com.example.demo.repository;

import com.example.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Student,String> {
    Student findByClassOf(int classOf);

    Student findByIsAdmin(boolean b);
}
