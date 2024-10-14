package com.example.demo.service.services;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.AuthenticationResponse;
import com.example.demo.config.JwtService;
import com.example.demo.domain.entities.Role;
import com.example.demo.domain.entities.User;
import com.example.demo.repository.repositores.UserRepository;
import com.example.demo.service.exceptions.userExceptions.UserIllegalArgumentException;
import com.example.demo.service.interfaces.UserServiceInterface;
import com.example.demo.service.services.dto.userDto.UserCreateDTO;
import com.example.demo.service.services.dto.userDto.UserDTO;
import com.example.demo.service.services.dto.userDto.UserLoginDTO;
import com.example.demo.service.services.dto.userDto.UserUpdateDTO;


@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Transactional
	@Override
	public List<UserDTO> getUsers() {
		return repository.getUsers().stream().map(user->new UserDTO(user.getId(),user.getUsername(),user.getEmail(),user.getAddress(),user.getPhone(),user.getRole())).collect(Collectors.toList());
	}
	
	@Override
	public UserDTO getUser(int id) {
		if(!(id>0)) {
			throw new UserIllegalArgumentException(id);
		}
		
		User user = repository.getUser(id);
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		
		return userDTO;
	}

	@Override
	public AuthenticationResponse createUser(UserCreateDTO user) {
		if(user == null) {
			throw new UserIllegalArgumentException(user);
		}
		
		if(user.getUsername()==null
				&& user.getPassword()==null
				&& user.getEmail()==null
				&& user.getAddress()==null
				&& user.getPhone()==null)
		{
			throw new UserIllegalArgumentException(user);
		}
		
		List<User> users = repository.getUsers();
		
		for(User u:users) {
			if(u.getEmail()==user.getEmail()) {
				throw new UserIllegalArgumentException(user.getEmail());
			}
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		
		User userForCreating = modelMapper.map(user, User.class);
		if(user.getRole() == "KORISNIK") {
			userForCreating.setRole(Role.KORISNIK);
		}
		if(user.getRole() == "IZDAVAC") {
			userForCreating.setRole(Role.IZDAVAC);
		}
		
		User createdUser = repository.createUser(userForCreating);
		
		var token = jwtService.generateToken(createdUser);
		
		AuthenticationResponse response = new AuthenticationResponse(token,createdUser.getId(),createdUser.getUsername(),createdUser.getEmail());
		
		return response;
	}
	
	@Override
	public AuthenticationResponse login(UserLoginDTO user) {

		System.out.println("Password from login request: " + user.getPassword());
		manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		User user2 = repository.findByEmail(user.getEmail());
		var token = jwtService.generateToken(user2);
		
		AuthenticationResponse response = new AuthenticationResponse(token,user2.getId(),user2.getUsername(),user2.getEmail());
		
		return response;
	}

	@Override
	public void deleteUser(int id) {
		repository.deleteUser(id);
	}

	@Override
	public UserDTO updateUser(UserUpdateDTO user) {
		if(user==null) {
			throw new UserIllegalArgumentException(user);
		}
		
		User userForUpdate = repository.getUser(user.getId());
		
		if(user.getUsername()!=null) {
			userForUpdate.setUsername(user.getUsername());
		}
		if(user.getPassword()!=null) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryptedPassword = bcrypt.encode(user.getPassword());
			user.setPassword(encryptedPassword);
		}
		else {
			user.setPassword(userForUpdate.getPassword());
		}
		if(user.getEmail()!=null) {
			userForUpdate.setEmail(user.getEmail());
		}
		if(user.getRole()!=null) {
			userForUpdate.setRole(user.getRole());
		}
		if(user.getAddress()!=null) {
			userForUpdate.setAddress(user.getAddress());
		}
		if(user.getPhone()!=null) {
			userForUpdate.setPhone(user.getPhone());
		}
		
		User userForUpdate2 = modelMapper.map(user, User.class);
		User updatedUser = repository.updateUser(userForUpdate2);
		UserDTO userDTO = modelMapper.map(updatedUser, UserDTO.class);
		return userDTO;
	}

}
