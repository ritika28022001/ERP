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
@Table(name = "ProductVariant")
public class ProductVariant {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "productVariantId", nullable = false, length = 200)
	private int productVariantId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product_id;
	
	@ManyToOne
	@JoinColumn(name = "variant_id")
	private Variant variant_id;

	public int getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	public Product getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public Variant getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(Variant variant_id) {
		this.variant_id = variant_id;
	}

	public ProductVariant(int productVariantId, Product product_id, Variant variant_id) {
		super();
		this.productVariantId = productVariantId;
		this.product_id = product_id;
		this.variant_id = variant_id;
	}

	public ProductVariant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
