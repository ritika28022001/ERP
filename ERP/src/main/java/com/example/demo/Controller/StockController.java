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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Stock;
import com.example.demo.Entity.Warehouse;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Repository.StockRepo;
import com.example.demo.Repository.WarehouseRepo;

@RestController
@RequestMapping
public class StockController {

	@Autowired
	private StockRepo stockRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private WarehouseRepo warehouseRepo;
	
	@GetMapping("/stock")
	public ResponseEntity<Map<String, Object>> listBrand(
			@RequestParam(defaultValue = "1")Integer page,
			
			@RequestParam(defaultValue = "5")Integer pageSize,
			
			@RequestParam(defaultValue = "DESC") String DIR){
		
		
			List<Stock> brands = new ArrayList<Stock>();
			PageRequest pageRequest;
			Pageable paging;
   
			  pageRequest = PageRequest.of(page -1, pageSize, Sort.by(Sort.Direction.DESC, "stock_id"));
		      paging = pageRequest;
		      Page<Stock> pageBrands;
		      pageBrands = stockRepo.findByFilterParam(paging);
		      brands = pageBrands.getContent();
		      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			     for(int i=0;i<brands.size();i++) {
			    	 Map<String, Object> poMap = new HashMap<String, Object>();
			    	
			    	 poMap.put("Id", brands.get(i).getStockId());
			    	 poMap.put("warehouseId", brands.get(i).getWarehouse_id());
			    	 poMap.put("lastUpdatedDate", brands.get(i).getLastUpdatedDate());
			    	 poMap.put("quantity", brands.get(i).getQuantity());
			    	 poMap.put("productId", brands.get(i).getProduct_id());
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

	@PostMapping("/stock")
    public ResponseEntity<?> stock(@RequestBody Stock stock){
		Stock stock2 = new Stock();
		stock2.setStockId(stock.getStockId());
		stock2.setQuantity(stock.getQuantity());
		stock2.setLastUpdatedDate(stock.getLastUpdatedDate());
		Product product= new Product();
		product= productRepo.getById(Integer.parseInt(stock.getProductId().toString()));
		stock2.setProduct_id(product);
		Warehouse warehouse= new Warehouse();
		warehouse= warehouseRepo.getById(Integer.parseInt(stock.getWarehouseId().toString()));
		stock2.setWarehouse_id(warehouse);
		stockRepo.save(stock2);
		return new ResponseEntity<>("saved" , HttpStatus.OK);
		
	}
	
	@PutMapping("/stock/{id}")
	public ResponseEntity<?> stock(@PathVariable Integer id,@RequestBody Stock stock){
		Stock stock2 = stockRepo.findById(id).get();
		stock2.setStockId(stock.getStockId());
		stock2.setQuantity(stock.getQuantity());
		stock2.setLastUpdatedDate(stock.getLastUpdatedDate());
		Product product= new Product();
		product= productRepo.getById(Integer.parseInt(stock.getProductId().toString()));
		stock2.setProduct_id(product);
		Warehouse warehouse= new Warehouse();
		warehouse= warehouseRepo.getById(Integer.parseInt(stock.getWarehouseId().toString()));
		stock2.setWarehouse_id(warehouse);
		stockRepo.save(stock2);
		return new ResponseEntity<>("saved" , HttpStatus.OK);
	
	}
}
