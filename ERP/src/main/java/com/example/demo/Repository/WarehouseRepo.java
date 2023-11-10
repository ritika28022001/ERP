package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Warehouse;

public interface WarehouseRepo extends JpaRepository<Warehouse, Integer>{

	@Query(value = "select * from Warehouse", nativeQuery = true)
	Page<Warehouse> findByFilterParam(Pageable paging);

}
