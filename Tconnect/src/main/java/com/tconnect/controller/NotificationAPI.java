package com.tconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tconnect.dto.ResponseDTO;
import com.tconnect.entity.Notification;
import com.tconnect.exception.JobPortalException;
import com.tconnect.service.NotificationService;


@RestController
@CrossOrigin
@RequestMapping("/notification")
@Validated
public class NotificationAPI {
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<Notification>>getNotifications(@PathVariable Long userId){
		return new ResponseEntity<>(notificationService.getUnreadNotifications(userId), HttpStatus.OK);
	}
	@PutMapping("/read/{id}")
	public ResponseEntity<ResponseDTO>readNotification(@PathVariable Long id) throws JobPortalException{
		notificationService.readNotification(id);
		return new ResponseEntity<>(new ResponseDTO("Success"), HttpStatus.OK);
	}
}

