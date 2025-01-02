package com.tconnect.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tconnect.dto.UserDTO;
import com.tconnect.entity.User;
import com.tconnect.repository.UserRepository;
import com.tconnect.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		User user = userDtoToUser(userDTO);
		
		User savedUser = this.userRepository.save(user);
		return this.userToUserDto(savedUser);
	}
	
	public User userDtoToUser(UserDTO userDTO) {
		
		return this.modelMapper.map(userDTO, User.class);
	}
	
	public UserDTO userToUserDto(User user) {
		
		return this.modelMapper.map(user, UserDTO.class);
	}

}
