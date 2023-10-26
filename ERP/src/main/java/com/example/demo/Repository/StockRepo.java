package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer>{

}
