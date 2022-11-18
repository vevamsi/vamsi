package com.nursery.repos;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nursery.models.Customer;
import com.nursery.models.Plant;

//import antlr.collections.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value="select * from Customer where userid=:p1",nativeQuery = true)
	public Customer findByUserid(@Param("p1")String userid);
	
	@Query(value="select count(userid) from Customer where userid=:p1",nativeQuery=true)
	public int getData(@Param("p1") String emailid);

	@Query(value="select * from customer where name ilike :p%",nativeQuery = true)	
	public List<Customer> findByName(String search);
	//List<customer> findByName(@Param("p") String search);
}
