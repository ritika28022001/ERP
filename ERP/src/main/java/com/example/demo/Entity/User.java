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
@Table(name = "Employee")
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "userId", nullable = false, length = 200)
	private int userId;
	
	@Column(name = "userName")
	private String userName;
	
	
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department_id;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Department getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Department department_id) {
		this.department_id = department_id;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public User(int userId, String userName, Department department_id, Company company_id) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.department_id = department_id;
		this.company_id = company_id;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
