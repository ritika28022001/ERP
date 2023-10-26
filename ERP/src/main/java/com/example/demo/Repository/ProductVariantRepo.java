package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ProductVariant;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Integer> {

}
