package com.myretail.product.api.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ProductPrice")
public class ProductPriceEntity {

	@Id
	private long id;
	private BigDecimal price;
	private String currency;
	
	public ProductPriceEntity(long id, BigDecimal price, String currency) {
		super();
		this.id = id;
		this.setPrice(price);
		this.setCurrency(currency);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
