package com.example.demo.repository.repositores;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entities.Review;
import com.example.demo.repository.interfaces.ReviewRepositoryInterface;
import com.example.demo.service.exceptions.ReviewsNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

@Repository
public class ReviewRepository implements ReviewRepositoryInterface {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Review> getReviewsForProperty(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Review> query = session.createQuery(
				"from Review r where r.property.Id=:id",
		        Review.class
		    );
		query.setParameter("id", id);
		try {
			List<Review> reviews = query.getResultList();
			return reviews;
		}catch (NoResultException e) {
			throw new ReviewsNotFoundException(id);	
		}
	}

	@Override
	public Review createReview(Review review) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(review);
		return review;
	}

}
