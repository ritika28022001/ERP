package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
	
	@Query(value = "select * from Company " , nativeQuery = true)
	Page<Company> findByFilterParam(Pageable paging);

}
