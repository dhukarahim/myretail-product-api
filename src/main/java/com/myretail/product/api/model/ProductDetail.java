package com.myretail.product.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetail {
	
	private long id;
	private String name;
	@JsonProperty("current_price")
	private ProductPrice currentPrice;

	public ProductDetail(long id, String name, ProductPrice currentPrice) {
		super();
		this.id = id;
		this.name = name;
		this.setCurrentPrice(currentPrice);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(ProductPrice currentPrice) {
		this.currentPrice = currentPrice;
	}


}
