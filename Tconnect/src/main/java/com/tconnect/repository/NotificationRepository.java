package com.tconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tconnect.dto.NotificationStatus;
import com.tconnect.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
