package com.example.demo.Controller;

import java.math.BigDecimal;
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
import com.example.demo.Entity.OrderDetails;
import com.example.demo.Entity.OrderMaster;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.PurchaseOrderItems;
import com.example.demo.Entity.User;
import com.example.demo.Repository.CustomerRepo;
import com.example.demo.Repository.FinancialYearRepo;
import com.example.demo.Repository.OrderDetailsRepo;
import com.example.demo.Repository.OrderMasterRepo;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Repository.UserRepo;

@RestController
@RequestMapping
public class OrderController {

	@Autowired
	private OrderMasterRepo orderMasterRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FinancialYearRepo financialYearRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	@GetMapping("/order")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "1")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<OrderMaster> brands = new ArrayList<OrderMaster>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "order_id"));
		      paging = pageRequest;
		      Page<OrderMaster> pageBrands;
		      pageBrands = orderMasterRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	 poMap.put("orderId", brands.get(i).getOrderId());
			    	 poMap.put("orderNo", brands.get(i).getOrderNo());
			    	 poMap.put("customerId", brands.get(i).getCustomer_id());
			    	 poMap.put("financialYearId", brands.get(i).getFinancial_year_id());
			    	 poMap.put("orderDate", brands.get(i).getOrderDate());
			    	 poMap.put("totalAmount", brands.get(i).getTotalAmount());
			    	 poMap.put("userId", brands.get(i).getUser_id());
			    	 
			    	 Integer idInteger = brands.get(i).getOrderId();
			    	 List<OrderDetails> orderDetails = new ArrayList<>();
			    	 orderDetails= orderDetailsRepo.findByItem(idInteger);
			    	 List<Map<String, Object>> iList = new ArrayList<Map<String, Object>>();
				       
			         for (int j = 0; j <  orderDetails.size(); j++) {
			        	 Map<String, Object> itemdeatilsmap = new HashMap<String, Object>();
			        	 itemdeatilsmap.put("orderDetailsId", orderDetails.get(j).getOrderDetailsId());
			        	 itemdeatilsmap.put("quantity", orderDetails.get(j).getQuantity());
			        	 itemdeatilsmap.put("subtotal", orderDetails.get(j).getSubTotal());
			        	 itemdeatilsmap.put("productId", orderDetails.get(j).getProduct_id());
			        	 iList.add(itemdeatilsmap);
			         }
			         poMap.put("orderdetails", iList);
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

	@PostMapping("/order")
	public ResponseEntity<?> order(@RequestBody OrderMaster orderMaster){
		OrderMaster orderMaster2 = new OrderMaster();
		orderMaster2.setOrderId(orderMaster.getOrderId());
		orderMaster2.setOrderDate(orderMaster.getOrderDate());
		orderMaster2.setOrderNo(orderMaster.getOrderNo());
		orderMaster2.setTotalAmount(orderMaster.getTotalAmount());
		
		Customer customer = new Customer();
		customer = customerRepo.getById(Integer.parseInt(orderMaster.getCustomerId().toString()));
		orderMaster2.setCustomer_id(customer);
		FinancialYear financialYear= new FinancialYear();
		financialYear = financialYearRepo.getById(Integer.parseInt(orderMaster.getFinancialYearId().toString()));
		orderMaster2.setFinancial_year_id(financialYear);
		User user = new User();
		user = userRepo.getById(Integer.parseInt(orderMaster.getUserId().toString()));
		orderMaster2.setUser_id(user);
		orderMasterRepo.save(orderMaster2);
		
		if(orderMaster.getOrderdetails()!=null)
			for (Map<String, Object> listMap : orderMaster.getOrderdetails()) {
				OrderDetails orderDetails= new OrderDetails();
				orderDetails.setOrderDetailsId(new Integer(listMap.get("orderDetailsId").toString()));
				orderDetails.setQuantity(new Integer(listMap.get("quantity").toString()));
				orderDetails.setSubTotal(new BigDecimal(listMap.get("subTotal").toString()));
				orderDetails.setOrder_id(orderMaster2);
				Product product= new Product();
				product=productRepo.getById(Integer.parseInt(listMap.get("productId").toString()));
				orderDetails.setProduct_id(product);
				orderDetailsRepo.save(orderDetails);
				
			}
		return new ResponseEntity<>("saved", HttpStatus.OK); 
	}
}
