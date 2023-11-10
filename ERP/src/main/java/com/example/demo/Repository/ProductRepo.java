package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query(value = "select * from Product" , nativeQuery = true)
	Page<Product> findByFilterParam(Pageable paging);

}
