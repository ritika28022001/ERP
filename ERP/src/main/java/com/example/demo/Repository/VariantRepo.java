package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Variant;
import com.example.demo.Entity.Vendor;

public interface VariantRepo extends JpaRepository<Vendor, Integer>{

	@Query(value = "select * from Variant", nativeQuery =  true )
	Page<Variant> findByFilterParam(Pageable paging);

}
