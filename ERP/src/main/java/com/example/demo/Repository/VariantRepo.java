package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Vendor;

public interface VariantRepo extends JpaRepository<Vendor, Integer>{

}
