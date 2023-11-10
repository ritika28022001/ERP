package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.PurchaseOrder;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer>{

	@Query(value = "select * from purchase_order", nativeQuery = true)
	Page<PurchaseOrder> findByFilterParam(Pageable paging);

}
