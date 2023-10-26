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
@Table(name = "Vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "vendorId", nullable = false, length = 200)
	private int vendorId;
	
	@Column(name = "vendorName")
	private String vendorName;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Vendor(int vendorId, String vendorName, Company company_id) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.company_id = company_id;
	}

	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

