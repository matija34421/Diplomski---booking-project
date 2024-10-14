package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.service.services.dto.propertyDto.ReviewCreateDTO;
import com.example.demo.service.services.dto.reviewDto.ReviewDTO;

public interface ReviewServiceInterface {
	List<ReviewDTO> getReviewsForProperty(int id);
	ReviewDTO createReviews(ReviewCreateDTO dto);
}
