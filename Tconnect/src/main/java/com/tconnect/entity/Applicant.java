package com.tconnect.entity;

import java.time.LocalDateTime;
import java.util.Base64;

import com.tconnect.dto.ApplicantDTO;
import com.tconnect.dto.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long applicantId;
	private String name;
	private String email;
	private Long phone;
	private String website;

	@Lob
	private byte[] resume;

	private String coverLetter;
	private LocalDateTime timestamp;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus applicationStatus;

	private LocalDateTime interviewTime;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job; // owning side

	public ApplicantDTO toDTO() {
		return new ApplicantDTO(this.getApplicantId(), this.getName(), this.getEmail(), this.getPhone(),
				this.getWebsite(),
				this.getResume() != null ? Base64.getEncoder().encodeToString(this.getResume()) : null,
				this.getCoverLetter(), this.getTimestamp(), this.getApplicationStatus(), this.interviewTime);
	}

	public Applicant(Long applicantId, String name, String email, Long phone, String website, byte[] resume,
			String coverLetter, LocalDateTime timestamp, ApplicationStatus applicationStatus,
			LocalDateTime interviewTime) {
		this.applicantId = applicantId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.resume = resume;
		this.coverLetter = coverLetter;
		this.timestamp = timestamp;
		this.applicationStatus = applicationStatus;
		this.interviewTime = interviewTime;
	}

}
