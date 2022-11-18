package com.nursery.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.dtos.Response;
import com.nursery.models.Category;
import com.nursery.services.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired private CategoryService cservice;
	
	@PostMapping
	public ResponseEntity<?> saveCategory(@Valid @RequestBody Category cat) {
		cservice.save(cat);
		return Response.success("Category saved");
	}
	
	@GetMapping
	public List<Category> listall(){
		return cservice.listAll();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id,@Valid @RequestBody Category cat){
		cat.setId(id);
		cservice.save(cat);
		return Response.success("Category updated successfully");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
		cservice.deleteCategory(id);
		return Response.success("Category deleted successfully");
	}
}
