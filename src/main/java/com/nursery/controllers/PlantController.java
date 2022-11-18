package com.nursery.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nursery.dtos.ProductDTO;
import com.nursery.dtos.Response;
import com.nursery.models.Plant;
import com.nursery.services.PlantService;

@CrossOrigin
@RestController
@RequestMapping("/api/plants")
public class PlantController {

@Autowired PlantService bservice;
	
	@PostMapping
	public ResponseEntity<?> savePlant(ProductDTO dto) {
		System.out.println(dto);		
		bservice.addPlant(dto);
		return Response.success("Plant saved successfully");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updatePlant(Plant plant,@Nullable MultipartFile pic, @PathVariable("id") int id) {
		plant.setId(id);
		bservice.updatePlant(plant,pic);
		return Response.success(plant);		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findPlant(@PathVariable("id")int id) {
		Plant plant=bservice.findPlantById(id);
		return Response.success(plant);
	}
	
	@GetMapping
	public List<Plant> findAllProducts(String search) {
		List<Plant> result = null;
		if(search==null || search.length()==0) {
			result=bservice.allPlants();
		}else {
			result=bservice.searchPlants(search);
		}
		
		return result;
	}
	
	
	@GetMapping("cats/{id}")
	public List<Plant> findByCategory(@PathVariable("id") int catid) {
		List<Plant> result = new ArrayList<Plant>();
		
		for(Plant b : bservice.categoryPlants(catid)) {
			result.add(b);
		}
		
		return result;
	}
		
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		bservice.deletePlant(id);
		return Response.status(HttpStatus.OK);
	}
	
}
