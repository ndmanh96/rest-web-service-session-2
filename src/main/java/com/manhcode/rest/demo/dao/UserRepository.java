package com.manhcode.rest.demo.dao;

import com.manhcode.rest.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String name);
}
