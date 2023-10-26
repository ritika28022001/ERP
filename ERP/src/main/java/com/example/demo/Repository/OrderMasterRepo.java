package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.OrderMaster;

public interface OrderMasterRepo extends JpaRepository<OrderMaster, Integer> {

}
