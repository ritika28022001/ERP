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
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Department;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.DepartmentRepo;

@RestController
@RequestMapping
public class DepartmentController {
@Autowired 
private CompanyRepo companyRepo;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@GetMapping("/department")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Department> brands = new ArrayList<Department>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "department_name"));
		      paging = pageRequest;
		      Page<Department> pageBrands;
		      pageBrands = departmentRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("departmentId", brands.get(i).getDepartmentId());
			    	 poMap.put("departmentName", brands.get(i).getDepartmentName());
			    	 poMap.put("companyId", brands.get(i).getCompany_id());
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
	@PostMapping("/department")
	public ResponseEntity<?> department(@RequestBody Department department){
		Department department2 = new Department();
		department2.setDepartmentId(department.getDepartmentId());
		department2.setDepartmentName(department.getDepartmentName());
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(department.getCompanyId().toString()));
		department2.setCompany_id(company);
		departmentRepo.save(department2);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
	
	@PutMapping("/department/{id}")
	public ResponseEntity<?> updatedepartmnet(@PathVariable Integer idInteger, @RequestBody Department department){
		Department newDepartment = departmentRepo.findById(idInteger).get();
		newDepartment.setDepartmentId(department.getDepartmentId());
		newDepartment.setDepartmentName(department.getDepartmentName());
		Company company = new Company();
		company=companyRepo.getById(Integer.parseInt(department.getCompanyId().toString()));
		newDepartment.setCompany_id(company);
		departmentRepo.save(newDepartment);
		return new  ResponseEntity<>("saved", HttpStatus.OK);
		
	}
		
}
