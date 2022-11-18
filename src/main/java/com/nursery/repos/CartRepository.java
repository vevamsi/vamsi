package com.nursery.repos;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nursery.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query(value="select * from cart where user_id=:p1 order by id",nativeQuery = true)
	List<Cart> findByUserid(@Param("p1") int id);
	
	@Query(value="select * from cart where product_id=:p1 and user_id=:p2",nativeQuery = true)
	Optional<Cart> findItemInCart(@Param("p1") int prodid,@Param("p2") int userid);
	
	@Modifying
	@Transactional
	@Query(value="delete from cart where user_id=:p1",nativeQuery = true)
	void emptyCart(@Param("p1")int userid);
}

