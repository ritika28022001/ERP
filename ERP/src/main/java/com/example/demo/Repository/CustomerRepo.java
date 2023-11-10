package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query(value = "select * from Customer ", nativeQuery = true )
	Page<Customer> findByFilterParam(Pageable paging);

}
