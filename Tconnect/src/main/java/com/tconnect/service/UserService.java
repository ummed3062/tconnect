package com.tconnect.service;

import com.tconnect.dto.LoginDTO;
import com.tconnect.dto.ResponseDTO;
import com.tconnect.dto.UserDTO;
import com.tconnect.exception.JobPortalException;

public interface UserService {

	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;
	
	public UserDTO getUserByEmail(String email)throws JobPortalException;

	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

	public Boolean sendOTP(String email) throws  Exception;

	public Boolean verifyOtp( String email, String otp) throws JobPortalException;

	public ResponseDTO changePassword( LoginDTO loginDTO) throws JobPortalException;
	
}
