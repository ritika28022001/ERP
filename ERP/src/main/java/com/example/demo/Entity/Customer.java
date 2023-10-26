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
@Table(name = "Customer")
public class Customer {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "customerId", nullable = false, length = 200)
	private int customerId ;
	
	@Column(name = "customerName")
	private String customerName;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Customer(int customerId, String customerName, Company company_id) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.company_id = company_id;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
