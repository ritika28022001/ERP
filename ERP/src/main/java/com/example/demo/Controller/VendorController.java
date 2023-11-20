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
import com.example.demo.Entity.User;
import com.example.demo.Entity.Vendor;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.VendorRepo;

@RestController
@RequestMapping
public class VendorController {

	@Autowired 
	private CompanyRepo companyRepo;
	@Autowired
	private VendorRepo vendorRepo;
	
	@GetMapping("/vendor")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Vendor> brands = new ArrayList<Vendor>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "vendor_id"));
		      paging = pageRequest;
		      Page<Vendor> pageBrands;
		      pageBrands = vendorRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("Id", brands.get(i).getVendorId());
			    	 poMap.put("vendorName", brands.get(i).getVendorName());
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
		      response.put("data", list	);
		      response.put("pagination", pageContent);
		
			return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/vendor")
	public ResponseEntity<?> vendor(@RequestBody Vendor vendor){
		Vendor vendor2 = new Vendor();
		vendor2.setVendorId(vendor.getVendorId());
		vendor2.setVendorName(vendor.getVendorName());
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(vendor.getCompanyId().toString()));
		vendor2.setCompany_id(company);
		vendorRepo.save(vendor2);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
	@PutMapping("/vendor/{id}")
	public ResponseEntity<?> vendor(@PathVariable int id, @RequestBody Vendor vendor){
		Vendor vendor2= vendorRepo.findById(id).get();
		vendor2.setVendorId(vendor.getVendorId());
		vendor2.setVendorName(vendor.getVendorName());
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(vendor.getCompanyId().toString()));
		vendor2.setCompany_id(company);
		vendorRepo.save(vendor2);
		return new ResponseEntity<>("saved", HttpStatus.OK);
	}
}
