package com.nursery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.dtos.ProductDTO;
import com.nursery.models.Plant;
import com.nursery.repos.PlantRepository;
import com.nursery.utils.StorageService;

@Service
public class PlantService {

	@Autowired private StorageService storageService;
	@Autowired private PlantRepository dao;
	@Autowired private CategoryService catservice;
	
	public void addPlant(ProductDTO dto) {
		Plant b=ProductDTO.toEntity(dto);
		String photo=storageService.store(dto.getPic());
		b.setPhoto(photo);
		b.setCategory(catservice.findById(dto.getCategory()));
		dao.save(b);
	}

	public void updatePlant(Plant b,MultipartFile pic) {
		
		Plant plant=dao.findById(b.getId()).get();		
		if(pic==null) {
			b.setPhoto(plant.getPhoto());
		}else {
			String photo=storageService.store(pic);
			b.setPhoto(photo);
		}
		dao.save(b);
	}

	public void deletePlant(int id) {
		Plant entity=dao.findById(id).get();
		dao.delete(entity);
	}

	public List<Plant> allPlants() {
		return dao.findAll(Sort.by(Direction.DESC, "id"));
	}
	
	public List<Plant> searchPlants(String search) {
		System.out.println("search "+search);
		return dao.findByPnameorDescr(search);
	}

	public Plant findPlantById(int prodid) {
		return dao.findById(prodid).get();
	}

	public List<Plant> categoryPlants(int cat) {
		System.out.println(cat);
		// TODO Auto-generated method stub
		return dao.findByCategoryId(cat);
	}
}
