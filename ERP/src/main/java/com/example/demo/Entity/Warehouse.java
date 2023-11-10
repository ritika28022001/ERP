package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Warehouse")
public class Warehouse {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "warehouseId", nullable = false, length = 200)
	private int warehouseId;
	
	@Column(name = "warehouseName")
	private String warehouseName;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;

	@Transient
	private String companyId;
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Warehouse(int warehouseId, String warehouseName, String address, Company company_id) {
		super();
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.address = address;
		this.company_id = company_id;
	}

	public Warehouse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
