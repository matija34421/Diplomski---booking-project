package com.zavrsni.booking.infrastructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zavrsni.booking.domain.entities.User;
import com.zavrsni.booking.service.serviceImplementation.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getUsers();
	}
}
