package com.example.demo.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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
@Table(name = "OrderMaster")
public class OrderMaster {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "orderId", nullable = false, length = 200)
	private int orderId;
	
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "totalAmount")
	private BigDecimal totalAmount;
	
	@Column(name = "orderNo")
	private String orderNo;
	
	@ManyToOne
	@JoinColumn(name = "financial_year_id")
	private FinancialYear financial_year_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user_id;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer_id;
	@Transient
	private List<Map<String, Object>> orderdetails;
	
	
	public List<Map<String, Object>> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Map<String, Object>> orderdetails) {
		this.orderdetails = orderdetails;
	}
	@Transient
	private String financialYearId ;
	@Transient
	private String userId ;
	@Transient
	private String customerId ;
	

	public String getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(String financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public Customer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}

	public OrderMaster(int orderId, Date orderDate, BigDecimal totalAmount, String orderNo,
			FinancialYear financial_year_id, User user_id, Customer customer_id) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderNo = orderNo;
		this.financial_year_id = financial_year_id;
		this.user_id = user_id;
		this.customer_id = customer_id;
	}

	public OrderMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
