package com.example.demo.repository.interfaces;

import java.util.List;

import com.example.demo.domain.entities.Picture;

public interface PictureRepositoryInterface {
	List<Picture> getPicturesForProperty(int id);
	Picture createPicture(Picture picture);
	void deletePicture(int id);
}
