package com.example.demo.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FinancialYear")
public class FinancialYear {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "financialYearId", nullable = false, length = 200)
	private int financialYearId;
	
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;

	public int getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(int financialYearId) {
		this.financialYearId = financialYearId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public FinancialYear(int financialYearId, Date startDate, Date endDate) {
		super();
		this.financialYearId = financialYearId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public FinancialYear() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
