package com.nursery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.jaxb.SpringDataJaxb.SortDto;
import org.springframework.stereotype.Service;

import com.nursery.models.Customer;
import com.nursery.models.Order;
import com.nursery.repos.OrderRepository;

@Service
public class OrderService {

@Autowired OrderRepository dao;
	
	public Order saveOrder(Order order) {		
		return dao.save(order);
	}
	
	public void confirm(int id) {
		Order order=dao.findById(id).get();
		order.setStatus("Confirmed");
		dao.save(order);
	}

	
	public List<Order> getAllOrders() {		
		return dao.findAll(Sort.by(Direction.DESC, "orderid"));
	}

	
	public List<Order> getCustomerOrders(Customer customer) {
		
		return dao.findByCustomer(customer);
	}

	
	public Order findById(int id) {
		
		return dao.findById(id).get();
	}
	public void cancel(int id) {
		Order order=dao.findById(id).get();
		order.setStatus("Cancelled");
		dao.save(order);
	}
	
}
