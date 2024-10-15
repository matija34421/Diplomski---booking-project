package com.example.demo.repository.interfaces;

import java.util.List;
import com.example.demo.domain.entities.Review;

public interface ReviewRepositoryInterface {
	List<Review> getReviewsForProperty(int id);
	Review createReview(Review review);
}
