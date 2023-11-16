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

import com.example.demo.Entity.User;
import com.example.demo.Entity.Variant;
import com.example.demo.Repository.VariantRepo;


@RequestMapping
@RestController
public class VariantController {

	@Autowired
	private VariantRepo variantRepo;
	
	@GetMapping("/variant")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Variant> brands = new ArrayList<Variant>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "variant_name"));
		      paging = pageRequest;
		      Page<Variant> pageBrands;
		      pageBrands = variantRepo.findByFilterParam(paging);
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

//	@PostMapping("/variant")
//	public ResponseEntity<?> variant(@RequestBody Variant variant){
//		Variant variant2 = new Variant();
//		variant2.setVariantId(variant.getVariantId());
//		variant2.setVariantName(variant.getVariantName());
//		variantRepo.save(variant2);
//		return  new ResponseEntity<>("saved", HttpStatus.OK);
//	}
}
