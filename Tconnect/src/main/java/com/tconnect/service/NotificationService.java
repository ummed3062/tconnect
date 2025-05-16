package com.tconnect.service;

import java.util.List;

import com.tconnect.dto.NotificationDTO;
import com.tconnect.entity.Notification;
import com.tconnect.exception.JobPortalException;


public interface NotificationService {

	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException;
	public List<Notification> getUnreadNotifications(Long userId);
	public void readNotification(Long id) throws JobPortalException;
}
