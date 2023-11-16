package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.PurchaseOrderItems;

public interface PurchaseOrderItemsRepo extends JpaRepository<PurchaseOrderItems, Integer>{

	@Query(value = "select * from purchase_order_items where purchase_order_id=?1", nativeQuery = true)
	List<PurchaseOrderItems> findByItem(Integer idInteger);

}
