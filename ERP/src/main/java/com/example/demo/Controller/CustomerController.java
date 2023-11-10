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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Company;
import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.CustomerRepo;

@RequestMapping
@RestController
public class CustomerController {

	@Autowired
	private CompanyRepo companyRepo;
	@Autowired 
	private CustomerRepo customerRepo;
	
	@GetMapping("/customer")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Customer> brands = new ArrayList<Customer>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "customer_name"));
		      paging = pageRequest;
		      Page<Customer> pageBrands;
		      pageBrands = customerRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("customerID", brands.get(i).getCustomerId());
			    	 poMap.put("name", brands.get(i).getCustomerName());
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
		
	@PostMapping("/customer")
	public ResponseEntity<?> createuser(@RequestBody Customer customer){
		Customer newCustomer = new Customer();
		newCustomer.setCustomerId(customer.getCustomerId());
		newCustomer.setCustomerName(customer.getCustomerName());
		
	Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(customer.getCompanyId().toString()));
		
		newCustomer.setCompany_id(company);
		customerRepo.save(newCustomer);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
}
