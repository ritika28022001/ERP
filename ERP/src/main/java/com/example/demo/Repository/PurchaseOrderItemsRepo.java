package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PurchaseOrderItems;

public interface PurchaseOrderItemsRepo extends JpaRepository<PurchaseOrderItems, Integer>{

}
