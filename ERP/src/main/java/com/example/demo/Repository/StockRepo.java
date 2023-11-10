package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer>{

	@Query(value = "select * from stock" , nativeQuery = true )
	Page<Stock> findByFilterParam(Pageable paging);

}
