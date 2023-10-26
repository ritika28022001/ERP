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
@Table(name = "PurchaseOrderItems")
public class PurchaseOrderItems {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "purchaseOrderItemsId", nullable = false, length = 200)
	private int purchaseOrderItemsId;
	
	@Column(name = "Quantity")
	private Integer Quantity;
	
	@Column(name = "unitPrice")
	private BigDecimal unitPrice;
	
	@Column(name = "totalAmount")
	private BigDecimal totalAmount;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product_id;
	
	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchase_order_id;

	public int getPurchaseOrderItemsId() {
		return purchaseOrderItemsId;
	}

	public void setPurchaseOrderItemsId(int purchaseOrderItemsId) {
		this.purchaseOrderItemsId = purchaseOrderItemsId;
	}

	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public PurchaseOrder getPurchase_order_id() {
		return purchase_order_id;
	}

	public void setPurchase_order_id(PurchaseOrder purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}

	public PurchaseOrderItems(int purchaseOrderItemsId, Integer quantity, BigDecimal unitPrice, BigDecimal totalAmount,
			Product product_id, PurchaseOrder purchase_order_id) {
		super();
		this.purchaseOrderItemsId = purchaseOrderItemsId;
		Quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalAmount = totalAmount;
		this.product_id = product_id;
		this.purchase_order_id = purchase_order_id;
	}

	public PurchaseOrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
