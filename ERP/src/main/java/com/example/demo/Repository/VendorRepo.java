package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {

	@Query(value = "select * from Vendor" , nativeQuery = true)
	Page<Vendor> findByFilterParam(Pageable paging);

}
