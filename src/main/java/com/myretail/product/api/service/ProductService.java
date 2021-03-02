package com.myretail.product.api.service;

import com.myretail.product.api.model.Product;
import com.myretail.product.api.model.ProductURLInfo;

/**
 * Product Service interface layer, providing methods to retrieve, add and
 * update product.
 *
 * @author Rahim Dhuka
 */
public interface ProductService {

	/**
	 * This method receives an instance of {@link Product}. And will add product to NoSQL DB
	 *
	 * @param productDetail {@link Product} The product detail to be added
	 */
	public void addProduct(Product productDetail);

	/**
	 * This method receives an instance of {@link Product}. And will update product price  of existing product into NoSQL DB
	 *
	 * @param productDetail {@link Product} The product detail to be updated
	 */
	public void updateProduct(Product productDetail);

	/**
	 * 
	 * 
	 * @param productId - ID of the product from which product price needs to be retrieved
	 * @param urlInfo - url details of external source from which product description needs to be retrieved
	 * @return A {@link Product}
	 */
	public Product getProductByID(long productId, ProductURLInfo urlInfo);

}
