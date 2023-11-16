
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

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.FinancialYear;
import com.example.demo.Repository.FinancialYearRepo;

@RestController
@RequestMapping
public class FinancialYearController {

	@Autowired
	private FinancialYearRepo financialYearRepo;
	
	@GetMapping("/financialyear")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "1")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<FinancialYear> brands = new ArrayList<FinancialYear>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "start_date"));
		      paging = pageRequest;
		      Page<FinancialYear> pageBrands;
		      pageBrands = financialYearRepo.findByFilterParam(paging);
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
		@PostMapping("/financialyear")
		public ResponseEntity<?> financialyear(@RequestBody FinancialYear financialYear){
			FinancialYear financialYear2 = new FinancialYear();
			financialYear2.setFinancialYearId(financialYear.getFinancialYearId());
			financialYear2.setStartDate(financialYear.getStartDate());
			financialYear2.setEndDate(financialYear.getEndDate());
			financialYearRepo.save(financialYear2);
			return new ResponseEntity<>("saved", HttpStatus.OK);
		}
}
