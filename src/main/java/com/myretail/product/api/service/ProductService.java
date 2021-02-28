package com.myretail.product.api.service;

import com.myretail.product.api.model.ProductDetail;
import com.myretail.product.api.model.ProductURLInfo;

public interface ProductService {
	
	public void addProduct(ProductDetail productDetail);
	
	public void updateProduct(ProductDetail productDetail);
	
	public ProductDetail getProductByID (long productId, ProductURLInfo urlInfo);

}
