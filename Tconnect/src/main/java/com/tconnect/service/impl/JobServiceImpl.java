package com.tconnect.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tconnect.dto.ApplicantDTO;
import com.tconnect.dto.Application;
import com.tconnect.dto.ApplicationStatus;
import com.tconnect.dto.JobDTO;
import com.tconnect.dto.JobStatus;
import com.tconnect.dto.NotificationDTO;
import com.tconnect.entity.Applicant;
import com.tconnect.entity.Job;
import com.tconnect.exception.JobPortalException;
import com.tconnect.repository.JobRepository;
import com.tconnect.repository.UserRepository;
import com.tconnect.service.JobService;
import com.tconnect.service.NotificationService;

@Service("jobService")
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {

		userRepository.findById(jobDTO.getPostedBy()).orElseThrow(() -> new JobPortalException("EMPLOYER_NOT_FOUND"));
		if (jobDTO.getId() == 0) {
//			jobDTO.setId(Utilities.getNextSequenceId("jobs"));

			jobDTO.setId(null);
			jobDTO.setPostTime(LocalDateTime.now());
			NotificationDTO notiDto = new NotificationDTO();
			notiDto.setAction("Job Posted");
			notiDto.setMessage("Job Posted Successfully for " + jobDTO.getJobTitle() + " at " + jobDTO.getCompany());

			notiDto.setUserId(jobDTO.getPostedBy());
			notiDto.setRoute("/posted-jobs/" + jobDTO.getId());
			notificationService.sendNotification(notiDto);
		} else {
			Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
			if (job.getJobStatus().equals(JobStatus.DRAFT) || jobDTO.getJobStatus().equals(JobStatus.CLOSED))
				jobDTO.setPostTime(LocalDateTime.now());
		}
		return jobRepository.save(jobDTO.toEntity()).toDTO();
	}

	@Override
	public List<JobDTO> getAllJobs() throws JobPortalException {
		return jobRepository.findAll().stream().map((x) -> x.toDTO()).toList();
	}

	@Override
	public JobDTO getJob(Long id) throws JobPortalException {
		return jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND")).toDTO();
	}

	@Override
	public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException {
		Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

		List<Applicant> applicants = job.getApplicants();
		if (applicants == null) {
			applicants = new ArrayList<>();
		}

		// Check for existing applicant by email (or another unique field)
		boolean alreadyApplied = applicants.stream().anyMatch(app -> app.getEmail().equals(applicantDTO.getEmail()));

		if (alreadyApplied) {
			throw new JobPortalException("JOB_APPLIED_ALREADY");
		}

		// Convert DTO to Entity
		Applicant applicant = applicantDTO.toEntity();
		applicant.setApplicationStatus(ApplicationStatus.APPLIED);
		applicant.setTimestamp(LocalDateTime.now());

		// Don't reuse applicantId if it exists
		applicant.setApplicantId(applicantDTO.getApplicantId()); // Let it auto-generate

		// Very important: set the job to establish the owning side
		applicant.setJob(job);

		// Add to the list
		applicants.add(applicant);
		job.setApplicants(applicants);

		jobRepository.save(job); // cascade will save applicant too
	}

	@Override
	public List<JobDTO> getHistory(Long id, ApplicationStatus applicationStatus) {

		return jobRepository.findByApplicantIdAndApplicationStatus(id, applicationStatus).stream().map((x) -> x.toDTO())
				.toList();
	}

	@Override
	public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException {
		userRepository.findById(id).orElseThrow(() -> new JobPortalException("EMPLOYER_NOT_FOUND"));
		return jobRepository.findByPostedBy(id).stream().map((x) -> x.toDTO()).toList();
	}

	@Override
	public void changeAppStatus(Application application) throws JobPortalException {
	    Job job = jobRepository.findById(application.getId())
	            .orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));

	    boolean applicantFound = false;

	    for (Applicant x : job.getApplicants()) {
	        if (application.getApplicantId().equals(x.getApplicantId())) {
	            applicantFound = true;

	            x.setApplicationStatus(application.getApplicationStatus());

	            if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
	                x.setInterviewTime(application.getInterviewTime());

	                NotificationDTO notiDto = new NotificationDTO();
	                notiDto.setAction("Interview Scheduled");
	                notiDto.setMessage("Interview scheduled for job id: " + application.getId());
	                notiDto.setUserId(application.getApplicantId());
	                notiDto.setRoute("/job-history");

	                try {
	                    notificationService.sendNotification(notiDto);
	                } catch (JobPortalException e) {
	                    e.printStackTrace();
	                }
	            }

	            break; // applicant found and updated, no need to loop further
	        }
	    }

	    if (!applicantFound) {
	        throw new JobPortalException("APPLICANT_NOT_FOUND");
	    }

	    jobRepository.save(job);
	    System.out.println("END-------------------------");
	}


}
