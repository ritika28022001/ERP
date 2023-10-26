package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PurchaseInvoice;

public interface PurchaseInvoiceRepo extends JpaRepository<PurchaseInvoice, Integer> {

}
