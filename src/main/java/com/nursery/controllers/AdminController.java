package com.nursery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.dtos.DashboardDTO;
import com.nursery.dtos.LoginDTO;
import com.nursery.dtos.Response;
import com.nursery.models.Admin;
import com.nursery.services.AdminService;
import com.nursery.services.CustomerService;
import com.nursery.services.OrderService;
import com.nursery.services.PlantService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired AdminService adminService;
	@Autowired CustomerService catservice;
	@Autowired PlantService plantservice;
	@Autowired OrderService orderservice;
	
	@GetMapping("/dashboard")
	public ResponseEntity<?> dashboard(){
		DashboardDTO db=new DashboardDTO();
		db.setProducts(plantservice.allPlants().size());
		db.setCustomers(catservice.allCustomers().size());
		db.setOrders(orderservice.getAllOrders().size());
		return Response.success(db);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Admin admin=adminService.validate(dto.getUserid(),dto.getPwd());
		if(admin!=null)
			return Response.success(admin);
		else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> updateProfile(@RequestBody Admin admin) {
		adminService.updateAdmin(admin);
		return Response.status(HttpStatus.OK);
	}

}
