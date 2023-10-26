package com.example.demo.Entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "stockId", nullable = false, length = 200)
	private int stockId;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product_id;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse_id;
	
	@Column(name = "lastUpdatedDate")
	private Date lastUpdatedDate;
	
	@Column(name = "quantity")
	private Integer quantity;

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public Warehouse getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(Warehouse warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Stock(int stockId, Product product_id, Warehouse warehouse_id, Date lastUpdatedDate, Integer quantity) {
		super();
		this.stockId = stockId;
		this.product_id = product_id;
		this.warehouse_id = warehouse_id;
		this.lastUpdatedDate = lastUpdatedDate;
		this.quantity = quantity;
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
