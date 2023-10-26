package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
