package com.tconnect.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tconnect.dto.NotificationDTO;
import com.tconnect.dto.NotificationStatus;
import com.tconnect.entity.Notification;
import com.tconnect.exception.JobPortalException;
import com.tconnect.repository.NotificationRepository;
import com.tconnect.service.NotificationService;


@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void sendNotification(NotificationDTO notificationDTO) throws JobPortalException {
//		notificationDTO.setId(Utilities.getNextSequenceId("notification"));
		notificationDTO.setStatus(NotificationStatus.UNREAD);
		notificationDTO.setTimestamp(LocalDateTime.now());
		notificationRepository.save(notificationDTO.toEntity());
	}

	@Override
	public List<Notification> getUnreadNotifications(Long userId) {
		return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
	}

	@Override
	public void readNotification(Long id) throws JobPortalException {
		Notification noti=notificationRepository.findById(id).orElseThrow(()->new JobPortalException("No Notitication found"));
		noti.setStatus(NotificationStatus.READ);
		notificationRepository.save(noti);
		
	}
}
