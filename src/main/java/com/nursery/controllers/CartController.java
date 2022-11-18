package com.nursery.controllers;

import java.util.List;

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
import com.nursery.exception.CartException;
import com.nursery.models.Cart;
import com.nursery.models.UpdateCartDTO;
import com.nursery.services.CartService;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired private CartService service;
	@PostMapping
	public ResponseEntity<?> addItemToCart(@RequestBody Cart cart) throws CartException {
		if(service.checkItemInCart(cart.getProduct().getId(),cart.getUser().getId())) {
			throw new CartException("Item already in cart");
		}
		service.addToCart(cart);
		return Response.success("Item added to cart");
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateQty(@PathVariable("id")int id,@RequestBody UpdateCartDTO dto){
		dto.setId(id);
		service.updateQty(dto);
		return Response.success("Item updated to cart");
	}
	
	@GetMapping
	public List<Cart> listall(int custid){
		return service.findByUser(custid);
	}
		
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteItemFromCart(@PathVariable("id") int id){
		service.deleteFromCart(id);
		return Response.success("Cart Item deleted successfully");
	}
}
