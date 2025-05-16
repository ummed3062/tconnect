package com.tconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tconnect.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User>findByEmail(String email);
}
