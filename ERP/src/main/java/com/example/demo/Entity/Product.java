package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "productId", nullable = false, length = 200)
	private int productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Product(int productId, String productName, String description, Company company_id) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.company_id = company_id;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
