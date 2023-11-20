package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.Department;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.DepartmentRepo;
import com.example.demo.Repository.UserRepo;

@RestController
@RequestMapping
public class UserController {
@Autowired 
private UserRepo userRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired 
	private DepartmentRepo departmentRepo;
	@GetMapping("user")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<User> brands = new ArrayList<User>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "user_id"));
		      paging = pageRequest;
		      Page<User> pageBrands;
		      pageBrands = userRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("Id", brands.get(i).getUserId());
			    	 poMap.put("UserName", brands.get(i).getUserName());
			    	 poMap.put("CompanyId", brands.get(i).getCompany_id());
			    	 poMap.put("DepartmentId", brands.get(i).getDepartment_id());
			    	 list.add(poMap);
			     }
		      Map<String, Object> pageContent = new HashMap<>();
		      pageContent.put("currentPage", page);
		      pageContent.put("pageSize", pageBrands.getSize());
		      pageContent.put("totalPages", pageBrands.getTotalPages());
		      pageContent.put("totalElements", pageBrands.getTotalElements());
		      pageContent.put("sortDirection", DIR);
		      Map<String, Object> response = new HashMap<>();
		      response.put("data", list);
		      response.put("pagination", pageContent);
		
			return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<?> user(@RequestBody User user){
		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2.setUserName(user.getUserName());
		
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(user.getCompanyId().toString()));
		user2.setCompany_id(company);
		
		Department department = new Department();
		department= departmentRepo.getById(Integer.parseInt(user.getDepartmentId().toString()));
		user2.setDepartment_id(department);
		userRepo.save(user2);
		return new ResponseEntity<>("saved" , HttpStatus.OK);
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<?> user(@RequestBody User user , @PathVariable Integer id){
		User user2 = userRepo.findById(id).get();
		user2.setUserId(user.getUserId());
		user2.setUserName(user.getUserName());
		
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(user.getCompanyId().toString()));
		user2.setCompany_id(company);
		
		Department department = new Department();
		department= departmentRepo.getById(Integer.parseInt(user.getDepartmentId().toString()));
		user2.setDepartment_id(department);
		userRepo.save(user2);
		return new ResponseEntity<>("saved" , HttpStatus.OK);
	}
	}

