package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.service.services.dto.pictureDto.PictureCreateDTO;
import com.example.demo.service.services.dto.pictureDto.PictureDTO;

public interface PictureServiceInterface {
	List<PictureDTO> getPicturesForProperty(int id);
	void createPicture(PictureCreateDTO picture);
	void deletePicture(int id);
}
