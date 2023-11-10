package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	@Query(value = "select * from Department" , nativeQuery = true)
	Page<Department> findByFilterParam(Pageable paging);

}
