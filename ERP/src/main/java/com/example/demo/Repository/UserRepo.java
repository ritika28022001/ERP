package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	@Query(value = "select * from Employee", nativeQuery =  true)
	Page<User> findByFilterParam(Pageable paging);

}
