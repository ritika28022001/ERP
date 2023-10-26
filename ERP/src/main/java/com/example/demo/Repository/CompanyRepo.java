package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

}
