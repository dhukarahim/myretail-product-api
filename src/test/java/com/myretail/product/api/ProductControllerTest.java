package com.myretail.product.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.product.api.controller.ProductController;
import com.myretail.product.api.exception.ResourceNotFoundException;
import com.myretail.product.api.exception.ServiceException;
import com.myretail.product.api.model.Product;
import com.myretail.product.api.model.ProductPrice;
import com.myretail.product.api.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductControllerTest {

	@Mock
	private ProductServiceImpl mockService;

	@InjectMocks
	private ProductController controller;

	@Test
	public void testGetProductById() {
		ProductPrice price = new ProductPrice(new BigDecimal(20.99), "USD");
		Product product = new Product(2131, "", price);
		
		when(mockService.getProductByID(2131, null)).thenReturn(product);
		ResponseEntity<Product> actualProduct = controller.getProductByID(2131);
		assertNotNull(actualProduct);
		assertEquals(HttpStatus.OK, actualProduct.getStatusCode());
	}
	
	@Test
	public void testGetProductByIdResourceNotFound() {
		Mockito.doThrow(new ResourceNotFoundException("")).when(mockService).getProductByID(12, null);
		ResponseEntity<Product> actualProduct = controller.getProductByID(12);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.NOT_FOUND, actualProduct.getStatusCode());
	}
	
	@Test
	public void testGetProductByIdInternalServerError() {
		Mockito.doThrow(new ServiceException("")).when(mockService).getProductByID(10, null);
		ResponseEntity<Product> actualProduct = controller.getProductByID(10);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualProduct.getStatusCode());
	}
	
	/**
	 * POST - Success test for saving product successfully
	 */
	@Test
	public void testAddProduct() {
		ProductPrice price = new ProductPrice(new BigDecimal(20.99), "USD");
		Product product = new Product(2131, "", price);
		
		ResponseEntity<Object> actualProduct = controller.addProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.NO_CONTENT, actualProduct.getStatusCode());
	}
	
	/**
	 * POST - Failure test with invalid product ID
	 */
	@Test
	public void testAddProductWithBadProductID() {
		ProductPrice price = new ProductPrice(new BigDecimal(20.99), "USD");
		Product product = new Product(0, "", price);
		
		ResponseEntity<Object> actualProduct = controller.addProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, actualProduct.getStatusCode());
	}
	
	/**
	 * POST - Failure test with invalid Product Price
	 */
	@Test
	public void testAddProductWithBadProductPrice() {
		Product product = new Product(0, "", null);
		
		ResponseEntity<Object> actualProduct = controller.addProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, actualProduct.getStatusCode());
	}
	
	/**
	 * PUT - Success test for updating product successfully
	 */
	@Test
	public void testUpdateProduct() {
		ProductPrice price = new ProductPrice(new BigDecimal(20.99), "USD");
		Product product = new Product(2131, "", price);
		
		ResponseEntity<Object> actualProduct = controller.updateProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.NO_CONTENT, actualProduct.getStatusCode());
	}
	
	/**
	 * PUT - Failure test with invalid product ID
	 */
	@Test
	public void testUpdateProductWithBadProductID() {
		ProductPrice price = new ProductPrice(new BigDecimal(20.99), "USD");
		Product product = new Product(0, "", price);
		
		ResponseEntity<Object> actualProduct = controller.updateProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, actualProduct.getStatusCode());
	}
	
	/**
	 * PUT - Failure test with invalid Product Price
	 */
	@Test
	public void testUpdateProductWithBadProductPrice() {
		Product product = new Product(0, "", null);
		
		ResponseEntity<Object> actualProduct = controller.updateProduct(product);
		assertNotNull(actualProduct);
		assertNull(actualProduct.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, actualProduct.getStatusCode());
	}
	

}
