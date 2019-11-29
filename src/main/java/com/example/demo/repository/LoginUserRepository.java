package com.example.demo.repository;

import com.example.demo.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser,Integer> {
}
