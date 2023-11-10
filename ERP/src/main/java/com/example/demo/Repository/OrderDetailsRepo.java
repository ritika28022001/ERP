package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

	@Query(value ="select * from order_details where order_id=?1" , nativeQuery = true )
	List<OrderDetails> findByItem(Integer idInteger);

	//List<OrderDetails> findByItem(Integer idInteger);

}
