package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.OrderMaster;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer> {

	@Query(value = "select * from Order_master", nativeQuery = true)
	Page<OrderMaster> findByFilterParam(Pageable paging);

}
