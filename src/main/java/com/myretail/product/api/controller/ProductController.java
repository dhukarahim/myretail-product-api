package com.myretail.product.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.product.api.exception.ResourceNotFoundException;
import com.myretail.product.api.exception.ServiceException;
import com.myretail.product.api.model.Product;
import com.myretail.product.api.model.ProductURLInfo;
import com.myretail.product.api.service.ProductService;

/**
 * Rest Controller class for MyProduct API SpringBoot application
 *
 * @author Rahim Dhuka
 */
@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;

	@Autowired
	private ProductURLInfo urlInfo;

	/**
	 * REST end point for adding Product.
	 *
	 * @param product - A {@link Product} containing the 835 data.
	 * @return A {@link ResponseEntity}
	 */
	@PostMapping("/product")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		try {
			validateProduct(product);
			service.addProduct(product);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid Input for product POST end point", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			logger.error("Internal Service error for product POST end point", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * REST end point for modifying Product price.
	 *
	 * @param product - A {@link Product} containing the 835 data.
	 * @return A {@link ResponseEntity}
	 */
	@PutMapping("/product")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
		try {
			validateProduct(product);
			service.updateProduct(product);
		} catch (IllegalArgumentException e) {
			logger.error("Invalid Input for product PUT end point", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			logger.error("Internal Service error for product PUT end point", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * REST end point for retrieving Product.
	 *
	 * @param id - id of the product that needs to be retrieved.
	 * @return A {@link ResponseEntity<ProductDetail>}
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductByID(@PathVariable long id) {
		Product product = null;
		try {
			product = service.getProductByID(id, urlInfo);
		} catch (ResourceNotFoundException e) {
			logger.error("Received an invalid productId", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ServiceException e) {
			logger.error("Internal Service error for product PUT end point", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	private void validateProduct(Product product) {
		if (product.getId() <= 0L) {
			logger.error("productId should be greater than zero");
			throw new IllegalArgumentException("Product ID is invalid");
		}

		if (product.getCurrentPrice() == null || product.getCurrentPrice().getValue() == null
				|| product.getCurrentPrice().getCurrencyCode() == null) {
			logger.error("Product price or currency code cannot be null or empty");
			throw new IllegalArgumentException("Product Price is invalid");
		}

	}

}
