package com.example.demo.Entity;

import java.math.BigDecimal;
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
@Table(name = "PurchaseOrder")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "purchaseOrderId", nullable = false, length = 200)
	private int purchaseOrderId;
	
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "totalAmount")
	private BigDecimal totalAmount;
	
	@Column(name = "purchaseOrderNo")
	private String purchaseOrderNo;
	
	@ManyToOne
	@JoinColumn(name = "financial_year_id")
	private FinancialYear financial_year_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user_id;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company_id;
	
	@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor vendor_id;

	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(String purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public FinancialYear getFinancial_year_id() {
		return financial_year_id;
	}

	public void setFinancial_year_id(FinancialYear financial_year_id) {
		this.financial_year_id = financial_year_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Company getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}

	public Vendor getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(Vendor vendor_id) {
		this.vendor_id = vendor_id;
	}

	public PurchaseOrder(int purchaseOrderId, Date orderDate, BigDecimal totalAmount, String purchaseOrderNo,
			FinancialYear financial_year_id, User user_id, Company company_id, Vendor vendor_id) {
		super();
		this.purchaseOrderId = purchaseOrderId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.purchaseOrderNo = purchaseOrderNo;
		this.financial_year_id = financial_year_id;
		this.user_id = user_id;
		this.company_id = company_id;
		this.vendor_id = vendor_id;
	}

	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}