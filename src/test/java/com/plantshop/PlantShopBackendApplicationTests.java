package com.plantshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.nursery.PlantShopBackendApplication;
import com.nursery.models.Category;
import com.nursery.models.Plant;
import com.nursery.repos.PlantRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
@ContextConfiguration(classes = PlantShopBackendApplication.class)
@SpringBootTest
class PlantShopBackendApplicationTests {
	@Autowired 
	PlantRepository pr;

	@Test
	void testsavePlant() {
		Plant p=new Plant();
		p.setDescr("A darling of home gardeners everywhere, the Money Plant Golden is famous for its never give up attitude.");
		p.setPhoto("20fbe4474ff64cd29facf09cbd17083a.webp");
		p.setPname("Money Plant Golden");
		p.setPrice(399);
		Category c=new Category(1);
		p.setCategory(c);
		pr.save(p);
		//assertNotNull(pr.findById(1).get());
		assertNotNull(pr.findByPnameorDescr("Money Plant Golden"));
	}
	@Test
	void testdisplayplants() {
		List<Plant> l=pr.findAll();
		assertThat(l).size().isGreaterThan(0);
	}
	/*@Test
	void testdeleteplants() {
		Plant p= pr.findById(1).get();
		    pr.delete(p);
		    assertThat(pr.existsById(1)).isEqualTo(false);
		    
		   // assertThat(deletedProduct,is(null));
	}*/
	@Test
	void testUpdatePlant() {
		Plant p = pr.findById(2).get();
	    p.setPrice(999);
	     
	    pr.save(p);
	     
	    Plant updatedProduct = pr.findById(2).get();
	     
	    assertThat(updatedProduct.getPrice()).isEqualTo(999);
	}
	/*@Test
	void testPlantById() {
		    Plant p = pr.findById(9).get();    
		    assertThat(p.getId()).isEqualTo(9);
		}*/
	@Test
	void testPlantsByCategory() { 
		    Plant p = (Plant) pr.findByCategoryId(1).get(1); 
		    assertThat(p.getCategory().getId()).isEqualTo(1);
		}
	}


