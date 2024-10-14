package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.AuthenticationResponse;
import com.example.demo.service.services.UserService;
import com.example.demo.service.services.dto.userDto.UserCreateDTO;
import com.example.demo.service.services.dto.userDto.UserLoginDTO;

@RestController()
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserCreateDTO request){
		return ResponseEntity.ok(userService.createUser(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDTO request){
		return ResponseEntity.ok(userService.login(request));
	}
}
