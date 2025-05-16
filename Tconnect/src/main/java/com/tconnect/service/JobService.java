package com.tconnect.service;

import java.util.List;

import com.tconnect.dto.JobDTO;
import com.tconnect.exception.JobPortalException;


public interface JobService {

	public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;

//	public List<JobDTO> getAllJobs() throws JobPortalException;
//
//	public JobDTO getJob(Long id) throws JobPortalException;
//
//	public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalException;
//
//	public List<JobDTO> getHistory(Long id, ApplicationStatus applicationStatus);
//
//	public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalException;
//
//	public void changeAppStatus(Application application) throws JobPortalException;
	
}
