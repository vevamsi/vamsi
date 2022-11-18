package com.nursery.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.dtos.PlaceOrderDTO;
import com.nursery.dtos.Response;
import com.nursery.models.Cart;
import com.nursery.models.Customer;
import com.nursery.models.Order;
import com.nursery.models.OrderDetails;
import com.nursery.models.Payment;
import com.nursery.services.CartService;
import com.nursery.services.CustomerService;
import com.nursery.services.OrderDetailService;
import com.nursery.services.OrderService;
import com.nursery.services.PaymentService;
import com.nursery.services.PlantService;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired OrderService orderService;
	@Autowired CustomerService customerService;
	@Autowired PaymentService paymentService;
	@Autowired OrderDetailService orderDetailsService;	
	@Autowired PlantService plantService;
	@Autowired CartService cartservice;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {	
		Payment pmt=new Payment();
		BeanUtils.copyProperties(dto, pmt);
		Payment payment=paymentService.savePayment(pmt);
		Order order=new Order();
		order.setPayment(payment);
		Customer customer=customerService.findById(dto.getCustomerid());
		order.setCustomer(customer);
		System.out.println("Order"+order);
		Order orders=orderService.saveOrder(order);
		
		for(Cart cart : cartservice.findByUser(dto.getCustomerid())) {
			OrderDetails od=new OrderDetails();
			od.setOrder(orders);
			od.setQty(cart.getQty());
			od.setPlant(cart.getProduct());			
			orderDetailsService.saveOrderDetails(od);
		}
		
		cartservice.emptyCart(dto.getCustomerid());
		
		return Response.status(HttpStatus.OK);
	}
	
	@GetMapping("/confirm/{id}")
	public ResponseEntity<?> confirmOrder(@PathVariable("id") int id) {
		orderService.confirm(id);
		return Response.success("Confirmed");
	}
	
	@GetMapping
	public List<Order> findAllOrders(Optional<Integer> custid) {
		List<Order> result=null;
		if(custid.isPresent()) {
			Customer customer=customerService.findById(custid.get());
			 result= orderService.getCustomerOrders(customer);
		}else {
			result = orderService.getAllOrders();
		}
		return result;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
		Order order = orderService.findById(id);
		return Response.success(order);
	}
}
