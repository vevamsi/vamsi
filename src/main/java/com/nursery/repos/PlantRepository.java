package com.nursery.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nursery.models.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

	List<Plant> findByCategoryId(Integer cat);
	@Query(value="select * from plant where pname ilike :p% or descr ilike :p%",nativeQuery = true)	
	List<Plant> findByPnameorDescr(@Param("p") String search);
}
