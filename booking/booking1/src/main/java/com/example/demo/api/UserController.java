package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.exceptions.userExceptions.UserNotFoundException;
import com.example.demo.service.services.UserService;
import com.example.demo.service.services.dto.userDto.UserDTO;
import com.example.demo.service.services.dto.userDto.UserUpdateDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> usersDtos = service.getUsers();
		return ResponseEntity.ok(usersDtos);
	}
	
	@RequestMapping(value = "/users" , method = RequestMethod.OPTIONS)
	public ResponseEntity<Void> handleOptions() {
	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable int id){
		UserDTO userDTO = service.getUser(id);
		return ResponseEntity.ok(userDTO);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable int id) {
		try {
	        service.deleteUser(id);
	        return ResponseEntity.noContent().build();  // HTTP 204 No Content for successful deletion
	    } catch (UserNotFoundException ex) {
	        // Let the GlobalExceptionHandler handle this exception
	        throw ex;
	    }
	}
	
	@PutMapping("/users")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO user) {
		System.out.println("Received user for update: " + user);
		UserDTO updatedUser = service.updateUser(user);
		return ResponseEntity.ok(updatedUser);
	}
}
