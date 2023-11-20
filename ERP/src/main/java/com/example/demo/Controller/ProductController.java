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
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.ProductRepo;

@RestController
@RequestMapping
public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	@GetMapping("/product")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Product> brands = new ArrayList<Product>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "product_name"));
		      paging = pageRequest;
		      Page<Product> pageBrands;
		      pageBrands = productRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("Id", brands.get(i).getProductId());
			    	 poMap.put("Name", brands.get(i).getProductName());
			    	 poMap.put("Description", brands.get(i).getDescription());
			    	 poMap.put("CompanyId", brands.get(i).getCompany_id());
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

	
	@PostMapping("/product")
	public ResponseEntity<?> product(@RequestBody Product product){
		Product product2 = new Product();
		product2.setProductId(product.getProductId());
		product2.setProductName(product.getProductName());
		product2.setDescription(product.getDescription());
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(product.getCompanyId().toString()));
	    product2.setCompany_id(company);
		productRepo.save(product2);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> product(@PathVariable Integer idInteger, @RequestBody Product product){
	   Product newProduct = productRepo.findById(idInteger).get();
	   newProduct.setProductId(product.getProductId());
	   newProduct.setProductName(product.getProductName());
	   Company company = new Company();
	   company=companyRepo.getById(Integer.parseInt(product.getCompanyId().toString()));
		newProduct.setCompany_id(company);
		newProduct.setDescription(product.getDescription());
	   productRepo.save(newProduct);
		return new ResponseEntity<>("saved", HttpStatus.OK);
		
	}
}
