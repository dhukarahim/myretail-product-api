package com.myretail.product.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductPrice {
	
	private BigDecimal value;
	
	@JsonProperty("currency_code")
	private String currencyCode;
	
	public ProductPrice(BigDecimal value, String currencyCode) {
		super();
		this.setValue(value);
		this.setCurrencyCode(currencyCode);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
