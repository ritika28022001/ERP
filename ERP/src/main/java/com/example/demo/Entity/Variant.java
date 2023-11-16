package com.example.demo.Entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Variant")
public class Variant {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "variantId", nullable = false, length = 200)
	private int variantId;
	
	@Column(name = "variantName")
	private String variantName;

	public int getVariantId() {
		return variantId;
	}

	public void setVariantId(int variantId) {
		this.variantId = variantId;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public Variant(int variantId, String variantName) {
		super();
		this.variantId = variantId;
		this.variantName = variantName;
	}

	public Variant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
