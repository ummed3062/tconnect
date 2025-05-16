package com.tconnect.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tconnect.entity.OTP;


public interface OTPRepository extends JpaRepository<OTP, String> {
    List<OTP> findByCreationTimeBefore(LocalDateTime expiryTime);
}
