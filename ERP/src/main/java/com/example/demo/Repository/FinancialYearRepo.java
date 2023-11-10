package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.FinancialYear;

public interface FinancialYearRepo extends JpaRepository<FinancialYear, Integer> {

	@Query(value = "select * from financial_year" , nativeQuery = true)
	Page<FinancialYear> findByFilterParam(Pageable paging);

}
