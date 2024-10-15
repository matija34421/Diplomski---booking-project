package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.exceptions.propertyExceptions.PropertyNotFoundException;
import com.example.demo.service.services.PropertyService;
import com.example.demo.service.services.ReviewService;
import com.example.demo.service.services.dto.propertyDto.FullPropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyCreateDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyUpdateDTO;
import com.example.demo.service.services.dto.propertyDto.ReviewCreateDTO;
import com.example.demo.service.services.dto.reviewDto.ReviewDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PropertyController {
	@Autowired
	private PropertyService service;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/properties/all")
	public List<PropertyDTO> getProperties(){
		return service.getProperties();
	}
	
	@GetMapping("/properties/{id}")
	public ResponseEntity<FullPropertyDTO> getProperty(@PathVariable int id){
		FullPropertyDTO propertyDTO = service.getProperty(id);
		return ResponseEntity.ok(propertyDTO);
	}
	
	@GetMapping("/properties/user/{id}")
	public ResponseEntity<List<PropertyDTO>> getPropertiesForUser(@PathVariable int id){
		List<PropertyDTO> propertyDTO = service.getPropertiesForUser(id);
		return ResponseEntity.ok(propertyDTO);
	}
	
	@GetMapping("/properties/reviews/{id}")
	public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable int id){
		List<ReviewDTO> reviews = reviewService.getReviewsForProperty(id);
		return ResponseEntity.ok(reviews);
	}
	
	@PostMapping("/properties/reviews")
	public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewCreateDTO review){
		ReviewDTO reviews = reviewService.createReviews(review);
		return ResponseEntity.ok(reviews);
	}
	
	@PostMapping("/properties")
	public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyCreateDTO property) {
		PropertyDTO createdProperty = service.createProperty(property);
		return ResponseEntity.ok(createdProperty);
	}
	
	@DeleteMapping("/properties/{id}")
	public ResponseEntity<PropertyDTO> deleteProperty(@PathVariable int id) {
		try {
	        service.deleteProperty(id);
	        return ResponseEntity.noContent().build();
	    } catch (PropertyNotFoundException ex) {
	        throw ex;
	    }
	}
	
	@PutMapping("/properties")
	public ResponseEntity<FullPropertyDTO> updateProperty(@RequestBody PropertyUpdateDTO property) {
		FullPropertyDTO updateProperty = service.updateProperty(property);
		return ResponseEntity.ok(updateProperty);
	}
}
