package com.tconnect.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class Certification {
	private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
}

