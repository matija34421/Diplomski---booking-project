package com.example.demo.repository.repositores;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Picture;
import com.example.demo.repository.interfaces.PictureRepositoryInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import com.example.demo.service.exceptions.pictureExceptions.PictureNotFoundException;
import com.example.demo.service.exceptions.propertyExceptions.*;

@Repository
public class PictureRepository implements PictureRepositoryInterface{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<Picture> getPicturesForProperty(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Picture>query = session.createQuery("from Picture p where p.property.Id=:id", Picture.class);
		query.setParameter("id", id);
		try {
			List<Picture> pictures = query.getResultList();
			return pictures;
		}catch (NoResultException e) {
			throw new PropertyNotFoundException(id);	
		}
	}

	@Override
	public Picture createPicture(Picture picture) {
		Session session = entityManager.unwrap(Session.class);
		session.persist(picture);
		return picture;
	}

	@Override
	public void deletePicture(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query<Picture> query = session.createQuery("from Picture where id=:id", Picture.class);
		query.setParameter("id", id);
		Picture picture;
		try {
			picture = query.getSingleResult();
			session.remove(picture);
		}catch (NoResultException e) {
			throw new PictureNotFoundException(id);	
		}
	}

}
