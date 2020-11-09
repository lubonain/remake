package com.lbn.springboot.repository;

import com.lbn.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
}
