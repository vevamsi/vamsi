package com.nursery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.models.Order;
import com.nursery.models.OrderDetails;
import com.nursery.repos.OrderDetailsRepository;

@Service
public class OrderDetailService {
	@Autowired OrderDetailsRepository dao;
	
	public void saveOrderDetails(OrderDetails od) {
		dao.save(od);
	}

	public OrderDetails findById(int id) {
		return dao.findById(id).get();
	}

	public List<OrderDetails> findByOrder(Order order) {
		return dao.findByOrder(order);
	}
}
