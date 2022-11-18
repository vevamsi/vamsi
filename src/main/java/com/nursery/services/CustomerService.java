package com.nursery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exception.CustomerException;
import com.nursery.exception.UserAvailabilityException;
import com.nursery.models.Customer;
import com.nursery.models.Plant;
import com.nursery.repos.CustomerRepository;

@Service
public class CustomerService {
	@Autowired private CustomerRepository dao;

	
	public void registerCustomer(Customer cust) {
		try {
			if(dao.getData(cust.getUserid())>0) {
				throw new UserAvailabilityException("THe user Already exists/nTry logging in");
			}else {
				dao.save(cust);
			}
		}catch(UserAvailabilityException uae) {
			System.out.println(uae.getMessage());
		}
		
	}

	public List<Customer> allCustomers() {
		return dao.findAll();
	}

	
	public Customer findById(int id) {
		return dao.findById(id).orElse(null);
	}

	
	public Customer validate(String userid, String pwd) {
		Customer cc=dao.findByUserid(userid);
		if(cc!=null && cc.getPwd().equals(pwd)) {
			return cc;
		}
		return null;
	}
	
	
	public boolean verifyUserId(String userid) {
		return dao.findByUserid(userid)!=null;
	}

	
	public void updateProfile(Customer cust) {
		if(cust.getPwd().equals("") || cust.getPwd()==null) {
			cust.setPwd(findById(cust.getId()).getPwd());
		}
		dao.save(cust);	
	}
	public List<Customer> searchCustomers(String search) {
		System.out.println("search "+search);
		return dao.findByName(search);
	}
	
}
