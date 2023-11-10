package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.PurchaseInvoice;

public interface PurchaseInvoiceRepo extends JpaRepository<PurchaseInvoice, Integer> {

	@Query(value = "select * from purchase_invoice" , nativeQuery = true)
	Page<PurchaseInvoice> findByFilterParam(Pageable paging);

}
