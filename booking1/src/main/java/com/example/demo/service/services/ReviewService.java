package com.example.demo.service.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Review;
import com.example.demo.repository.repositores.PropertyRepository;
import com.example.demo.repository.repositores.ReviewRepository;
import com.example.demo.repository.repositores.UserRepository;
import com.example.demo.service.exceptions.propertyExceptions.PropertyIllegalArgumentException;
import com.example.demo.service.interfaces.ReviewServiceInterface;
import com.example.demo.service.services.dto.propertyDto.ReviewCreateDTO;
import com.example.demo.service.services.dto.reviewDto.ReviewDTO;

import jakarta.transaction.Transactional;

@Service
public class ReviewService implements ReviewServiceInterface{

	@Autowired
	ReviewRepository repository;
	@Autowired
	UserRepository user_repository;
	@Autowired
	PropertyRepository property_repository;
	
	@Transactional
	@Override
	public List<ReviewDTO> getReviewsForProperty(int id) {
		if(id<=0) {
			throw new PropertyIllegalArgumentException(id);
		}

		List<ReviewDTO> reviews = repository.getReviewsForProperty(id).stream().map(review -> new ReviewDTO(
			        review.getId(),
			        review.getProperty().getId(),
			        review.getUser().getEmail(),
			        review.getReview(),
			        review.getStar_number()
			    ))
			    .collect(Collectors.toList());

		return reviews;
	}

	@Transactional
	@Override
	public ReviewDTO createReviews(ReviewCreateDTO dto) {
		Review reviewForCreating = new Review();
		reviewForCreating.setProperty(property_repository.getProperty(dto.getPropertyId()));
		reviewForCreating.setUser(user_repository.getUser(dto.getUserId()));
		reviewForCreating.setReview(dto.getReview());
		reviewForCreating.setStar_number(dto.getStar_number());
		
		Review createdReview = repository.createReview(reviewForCreating);
		
		ReviewDTO reviewForFront = new ReviewDTO(createdReview.getId(),createdReview.getProperty().getId(),createdReview.getUser().getEmail(),createdReview.getReview(),createdReview.getStar_number());
		return reviewForFront;
		
	}

}
