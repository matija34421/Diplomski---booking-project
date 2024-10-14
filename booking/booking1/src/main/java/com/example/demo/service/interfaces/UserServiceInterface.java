package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.config.AuthenticationResponse;
import com.example.demo.service.services.dto.userDto.UserCreateDTO;
import com.example.demo.service.services.dto.userDto.UserDTO;
import com.example.demo.service.services.dto.userDto.UserLoginDTO;
import com.example.demo.service.services.dto.userDto.UserUpdateDTO;

public interface UserServiceInterface {
	List<UserDTO> getUsers();
	UserDTO getUser(int id);
	AuthenticationResponse createUser(UserCreateDTO user);
	AuthenticationResponse login(UserLoginDTO user);
	void deleteUser(int id);
	UserDTO updateUser(UserUpdateDTO user);
}
