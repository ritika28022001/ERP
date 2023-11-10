package com.example.demo.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.boot.registry.selector.StrategyRegistration;
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
import com.example.demo.Entity.FinancialYear;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.PurchaseOrder;
import com.example.demo.Entity.PurchaseOrderItems;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Vendor;
import com.example.demo.Repository.CompanyRepo;
import com.example.demo.Repository.FinancialYearRepo;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Repository.PurchaseOrderItemsRepo;
import com.example.demo.Repository.PurchaseOrderRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Repository.VendorRepo;

@RestController
@RequestMapping
public class PurchaseOrderController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FinancialYearRepo financialYearRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private VendorRepo vendorRepo;
	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;
	@Autowired
	
	private ProductRepo productRepo;
	@Autowired
	private PurchaseOrderItemsRepo purchaseOrderItemsRepo;
	@GetMapping("/purchaseorder")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<PurchaseOrder> brands = new ArrayList<PurchaseOrder>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "order_date"));
		      paging = pageRequest;
		      Page<PurchaseOrder> pageBrands;
		      pageBrands = purchaseOrderRepo.findByFilterParam(paging);
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

	@PostMapping("/purchaseorder")
	public ResponseEntity<?> purchaseorder (@RequestBody PurchaseOrder purchaseOrder){
		PurchaseOrder purchaseOrder2 = new PurchaseOrder();
		purchaseOrder2.setOrderDate(purchaseOrder.getOrderDate());
		purchaseOrder2.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
		purchaseOrder2.setPurchaseOrderNo(purchaseOrder.getPurchaseOrderNo());
		purchaseOrder2.setTotalAmount(purchaseOrder.getTotalAmount());
		Company company=  new Company();
		company= companyRepo.getById(Integer.parseInt(purchaseOrder.getCompanyId().toString()));
		purchaseOrder2.setCompany_id(company);
		FinancialYear financialYear= new FinancialYear();
		financialYear= financialYearRepo.getById(Integer.parseInt(purchaseOrder.getFinancialYearId().toString()));
		purchaseOrder2.setFinancial_year_id(financialYear);
		User user= new User();
		user= userRepo.getById(Integer.parseInt(purchaseOrder.getUserId().toString()));
		purchaseOrder2.setUser_id(user);
		Vendor vendor= new Vendor();
		vendor= vendorRepo.getById(Integer.parseInt(purchaseOrder.getVendorId().toString()));
		purchaseOrder2.setVendor_id(vendor);
		purchaseOrderRepo.save(purchaseOrder2);
		
		if(purchaseOrder.getOrderdetails()!= null)
			for (Map<String, Object> listMap : purchaseOrder.getOrderdetails()) {
				PurchaseOrderItems purchaseOrderItems= new PurchaseOrderItems();
				
				Product product = new Product();
				product = productRepo.getById(Integer.parseInt(listMap.get("productId").toString()));
				purchaseOrderItems.setProduct_id(product);
				purchaseOrderItems.setPurchaseOrderItemsId(new Integer(listMap.get("purchaseOrderItemsId").toString()));
				purchaseOrderItems.setQuantity(new Integer(listMap.get("quantity").toString()));
				purchaseOrderItems.setTotalAmount(new BigDecimal(listMap.get("TotalAmount").toString()));
				purchaseOrderItems.setUnitPrice(new BigDecimal(listMap.get("UnitPrice").toString()));
				purchaseOrderItems.setPurchase_order_id(purchaseOrder2);
				purchaseOrderItemsRepo.save(purchaseOrderItems);
			}
		return new ResponseEntity<>("saved", HttpStatus.OK);
		
	}
}
