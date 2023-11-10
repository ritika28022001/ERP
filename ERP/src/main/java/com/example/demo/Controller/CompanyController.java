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
import com.example.demo.Entity.Product;
import com.example.demo.Repository.CompanyRepo;

@RequestMapping
@RestController
public class CompanyController {

	
	@Autowired CompanyRepo companyRepo;
	@GetMapping("/company")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Company> brands = new ArrayList<Company>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "company_name"));
		      paging = pageRequest;
		      Page<Company> pageBrands;
		      pageBrands = companyRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      Map<String, Object> pageContent = new HashMap<>();
		      pageContent.put("currentPage", page);
		      pageContent.put("pageSize", pageBrands.getSize());
		      pageContent.put("totalPages", pageBrands.getTotalPages());
		      pageContent.put("totalElements", pageBrands.getTotalElements());
		      pageContent.put("sortDirection", DIR);
		      Map<String, Object> response = new HashMap<>();
		      response.put("data", brands);
		      response.put("pagination", pageContent);
		
			return new ResponseEntity<>(response, HttpStatus.OK);
		
		
}
	
	@PostMapping("/company")
	public ResponseEntity<?> createuser(@RequestBody Company company){
		Company newCompany = new Company();
		newCompany.setCompanyId(company.getCompanyId());
		newCompany.setCompanyName(company.getCompanyName());
		companyRepo.save(newCompany);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
	
	@PutMapping("/company/{id}")
	public ResponseEntity<?> updateuser(@PathVariable Integer id,@RequestBody Company company){
		Company newCompany= companyRepo.findById(id).get();
		newCompany.setCompanyId(company.getCompanyId());
		newCompany.setCompanyName(company.getCompanyName());
		companyRepo.save(newCompany);
		return new ResponseEntity<>("saved", HttpStatus.OK);
		
	}
	
	}
	

