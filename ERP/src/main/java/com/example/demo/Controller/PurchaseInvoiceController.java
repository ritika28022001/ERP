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
import com.example.demo.Entity.FinancialYear;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.PurchaseInvoice;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Vendor;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.FinancialYearRepo;
import com.example.demo.Repository.PurchaseInvoiceRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Repository.VendorRepo;

@RestController
@RequestMapping
public class PurchaseInvoiceController {

	@Autowired
	private PurchaseInvoiceRepo purchaseInvoiceRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private FinancialYearRepo financialYearRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private VendorRepo vendorRep;
	
	@GetMapping("/purchaseinvoice")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<PurchaseInvoice> brands = new ArrayList<PurchaseInvoice>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "purchase_invoice_id"));
		      paging = pageRequest;
		      Page<PurchaseInvoice> pageBrands;
		      pageBrands = purchaseInvoiceRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("InvoiceNo", brands.get(i).getPurchaseInvoiceNo());
			    	 poMap.put("CompanyId", brands.get(i).getCompany_id());
			    	 poMap.put("financialYearId", brands.get(i).getFinancial_year_id());
			    	 poMap.put("OrderDate", brands.get(i).getOrderDate());
			    	 poMap.put("totalAmount", brands.get(i).getTotalAmount());
			    	 poMap.put("userId", brands.get(i).getUser_id());
			    	 poMap.put("vendorId", brands.get(i).getVendor_id());
			    	 poMap.put("Id", brands.get(i).getPurchaseInvoiceId());
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
	
	@PostMapping("/purchaseinvoice")
	public ResponseEntity<?> purchase(@RequestBody PurchaseInvoice purchaseInvoice){
		PurchaseInvoice purchaseInvoice2 = new PurchaseInvoice();
		purchaseInvoice2.setPurchaseInvoiceId(purchaseInvoice.getPurchaseInvoiceId());
		purchaseInvoice2.setPurchaseInvoiceNo(purchaseInvoice.getPurchaseInvoiceNo());
		purchaseInvoice2.setTotalAmount(purchaseInvoice.getTotalAmount());
		purchaseInvoice2.setOrderDate(purchaseInvoice.getOrderDate());
		Company company = new Company();
		company = companyRepo.getById(Integer.parseInt(purchaseInvoice.getCompanyId().toString()));
		purchaseInvoice2.setCompany_id(company);
		FinancialYear financialYear = new FinancialYear();
		financialYear= financialYearRepo.getById(Integer.parseInt(purchaseInvoice.getFinancialYearId().toString()));
		purchaseInvoice2.setFinancial_year_id(financialYear);
		User user = new User();
		user= userRepo.getById(Integer.parseInt(purchaseInvoice.getUserId().toString()));
		purchaseInvoice2.setUser_id(user);
		Vendor vendor = new Vendor();
		vendor= vendorRep.getById(Integer.parseInt(purchaseInvoice.getVendorId().toString()));
		purchaseInvoice2.setVendor_id(vendor);
		purchaseInvoiceRepo.save(purchaseInvoice2);
		return new ResponseEntity<>("saved" ,HttpStatus.OK);
		
	}
	
	@PutMapping("/purchaseinvoice/{id}")
	public ResponseEntity<?> po(@PathVariable Integer id, @RequestBody PurchaseInvoice purchaseInvoice){
		PurchaseInvoice newInvoice= purchaseInvoiceRepo.findById(id).get();
		newInvoice.setPurchaseInvoiceId(purchaseInvoice.getPurchaseInvoiceId());
		newInvoice.setPurchaseInvoiceNo(purchaseInvoice.getPurchaseInvoiceNo());
		newInvoice.setOrderDate(purchaseInvoice.getOrderDate());
		newInvoice.setTotalAmount(purchaseInvoice.getTotalAmount());
		Company company = new Company();
		company=companyRepo.getById(Integer.parseInt(purchaseInvoice.getCompanyId().toString()));
		newInvoice.setCompany_id(company);
		FinancialYear financialYear =financialYearRepo.getById(Integer.parseInt(purchaseInvoice.getFinancialYearId().toString()));
		newInvoice.setFinancial_year_id(financialYear);
		User user= userRepo.getById(Integer.parseInt(purchaseInvoice.getUserId().toString()));
		newInvoice.setUser_id(user);
		Vendor vendor= vendorRep.getById(Integer.parseInt(purchaseInvoice.getVendorId()));
		newInvoice.setVendor_id(vendor);
	    purchaseInvoiceRepo.save(newInvoice);
		
		return new ResponseEntity<>("saved",HttpStatus.OK);
		
	}
}
