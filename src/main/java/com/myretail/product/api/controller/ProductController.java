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
import com.myretail.product.api.model.ProductDetail;
import com.myretail.product.api.model.ProductURLInfo;
import com.myretail.product.api.service.ProductService;

@RestController
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	@Autowired
	private ProductURLInfo urlInfo;
	
	@PostMapping("/product")
	public ResponseEntity<Object> addProduct(@RequestBody ProductDetail product) {
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
	
	@PutMapping("/product")
	public ResponseEntity<Object> updateProduct(@RequestBody ProductDetail product) {
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

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDetail> getProductByID(@PathVariable long id) {
		ProductDetail product = null;
		try {
			product = service.getProductByID(id, urlInfo);
		} catch (ResourceNotFoundException e) {
			logger.error("Received an invalid productId", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (ServiceException e) {
			logger.error("Internal Service error for product PUT end point", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<> (product, HttpStatus.OK);
	}
	
	private void validateProduct(ProductDetail product) {
		if (product.getId() <= 0L) {
			logger.error("productId should be greater than zero");
			throw new IllegalArgumentException("Product ID is invalid");
		}
		
		if (product.getCurrentPrice() == null || product.getCurrentPrice().getValue() == null || product.getCurrentPrice().getCurrencyCode() == null) {
			logger.error("Product price or currency code cannot be null or empty");
			throw new IllegalArgumentException("Product Price is invalid");
		}
		
	}

}
