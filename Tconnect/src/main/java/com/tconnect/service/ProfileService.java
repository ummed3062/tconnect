package com.tconnect.service;

import java.util.List;

import com.tconnect.dto.ProfileDTO;
import com.tconnect.dto.UserDTO;
import com.tconnect.exception.JobPortalException;



public interface ProfileService {
	public Long createProfile(UserDTO userDTO) throws JobPortalException;

	public ProfileDTO getProfile(Long id) throws JobPortalException;

	public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalException;

	public List<ProfileDTO> getAllProfiles() throws JobPortalException;
}