package com.example.demo.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.exceptions.pictureExceptions.PictureNotFoundException;
import com.example.demo.service.services.PictureService;
import com.example.demo.service.services.dto.pictureDto.PictureCreateDTO;
import com.example.demo.service.services.dto.pictureDto.PictureDTO;
import com.example.demo.service.services.dto.propertyDto.PropertyDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PictureController {
	@Autowired
	private PictureService service;
	
	@GetMapping("/pictures/{id}")
	public ResponseEntity<List<PictureDTO>> getPicturesForProperty(@PathVariable int id){
		List<PictureDTO> pictureDTO = service.getPicturesForProperty(id);
		return ResponseEntity.ok(pictureDTO);
	}
	

	@PostMapping("/pictures/uploadPicture")
	public String uploadFile(@RequestParam("picture") MultipartFile file) {
	    if (file.isEmpty()) {
	        return "Please select a file to upload.";
	    }

	    try {
	        Path uploadPath = Paths.get("..\\..\\bookingFront\\src\\assets");

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        Path filePath = uploadPath.resolve(file.getOriginalFilename());

	        Files.write(filePath, file.getBytes());

	        return "File uploaded successfully: " + file.getOriginalFilename();

	    } catch (IOException e) {
	        e.printStackTrace();
	        return "File upload failed.";
	    }
	}
	
	@PostMapping("/pictures")
	public ResponseEntity<Map<String, Object>> createPicture(@RequestParam("picture") MultipartFile file,
	                                                         @RequestParam("picturePath") String picturePath,
	                                                         @RequestParam("property_id") int propertyId) {
	    Map<String, Object> response = new HashMap<>();
	    
	    if (file.isEmpty()) {
	        response.put("success", false);
	        response.put("message", "Please select a file to upload.");
	        return ResponseEntity.badRequest().body(response);
	    }

	    try {
	        Path uploadPath = Paths.get("..\\..\\bookingFront\\src\\assets");

	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        Path filePath = uploadPath.resolve(file.getOriginalFilename());

	        Files.write(filePath, file.getBytes());

	        PictureCreateDTO dto = new PictureCreateDTO(propertyId, picturePath);
	        service.createPicture(dto);

	        response.put("success", true);
	        response.put("message", "File uploaded successfully: " + file.getOriginalFilename());
	        return ResponseEntity.ok(response);

	    } catch (IOException e) {
	        e.printStackTrace();
	        response.put("success", false);
	        response.put("message", "File upload failed.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	@DeleteMapping("/pictures/{id}")
	public ResponseEntity<PropertyDTO> deletePicture(@PathVariable int id) {
		try {
	        service.deletePicture(id);
	        return ResponseEntity.noContent().build();
	    } catch (PictureNotFoundException ex) {
	        throw ex;
	    }
	}
}
