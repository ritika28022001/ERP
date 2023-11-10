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
@Table(name = "Department")
public class Department {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "departmentId", nullable = false, length = 200)
	private int departmentId;
	
	@Column(name = "departmentName")
	private String departmentName;
	
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Department(int departmentId, String departmentName, Company company_id) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.company_id = company_id;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
