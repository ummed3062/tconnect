package com.tconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tconnect.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
