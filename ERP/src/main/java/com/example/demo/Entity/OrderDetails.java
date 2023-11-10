package com.example.demo.Entity;

import java.math.BigDecimal;

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
@Table(name = "OrderDetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "orderDetailsId", nullable = false, length = 200)
	private int orderDetailsId;
	
	@Column(name = "Quantity")
	private Integer Quantity;
	
	@Column(name = "subTotal")
	private BigDecimal subTotal ;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product_id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderMaster order_id;

	@Transient
	private String productId;
	
	
	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public OrderMaster getOrder_id() {
		return order_id;
	}

	public void setOrder_id(OrderMaster order_id) {
		this.order_id = order_id;
	}

	public OrderDetails(int orderDetailsId, Integer quantity, BigDecimal subTotal, Product product_id,
			OrderMaster order_id) {
		super();
		this.orderDetailsId = orderDetailsId;
		Quantity = quantity;
		this.subTotal = subTotal;
		this.product_id = product_id;
		this.order_id = order_id;
	}

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
