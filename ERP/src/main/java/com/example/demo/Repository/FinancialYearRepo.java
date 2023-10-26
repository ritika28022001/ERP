package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.FinancialYear;

public interface FinancialYearRepo extends JpaRepository<FinancialYear, Integer> {

}
