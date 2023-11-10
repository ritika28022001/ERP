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
import javax.persistence.Transient;

@Entity
@Table(name = "PurchaseInvoice")
public class PurchaseInvoice {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "purchaseInvoiceId", nullable = false, length = 200)
	private int purchaseInvoiceId;
	
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "totalAmount")
	private BigDecimal totalAmount;
	
	@Column(name = "purchaseInvoiceNo")
	private String purchaseInvoiceNo;
	
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
	
	
	@Transient
	private String userId;
	
	@Transient 
	private String vendorId ;
	@Transient
	private String companyId ;
	@Transient
	private String financialYearId ;
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(String financialYearId) {
		this.financialYearId = financialYearId;
	}

	public int getPurchaseInvoiceId() {
		return purchaseInvoiceId;
	}

	public void setPurchaseInvoiceId(int purchaseInvoiceId) {
		this.purchaseInvoiceId = purchaseInvoiceId;
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

	public String getPurchaseInvoiceNo() {
		return purchaseInvoiceNo;
	}

	public void setPurchaseInvoiceNo(String purchaseInvoiceNo) {
		this.purchaseInvoiceNo = purchaseInvoiceNo;
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

	public PurchaseInvoice(int purchaseInvoiceId, Date orderDate, BigDecimal totalAmount, String purchaseInvoiceNo,
			FinancialYear financial_year_id, User user_id, Company company_id, Vendor vendor_id) {
		super();
		this.purchaseInvoiceId = purchaseInvoiceId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.purchaseInvoiceNo = purchaseInvoiceNo;
		this.financial_year_id = financial_year_id;
		this.user_id = user_id;
		this.company_id = company_id;
		this.vendor_id = vendor_id;
	}

	public PurchaseInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
