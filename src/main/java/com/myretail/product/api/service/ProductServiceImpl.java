package com.myretail.product.api.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.myretail.product.api.entity.ProductPriceEntity;
import com.myretail.product.api.exception.ResourceNotFoundException;
import com.myretail.product.api.exception.ServiceException;
import com.myretail.product.api.model.Product;
import com.myretail.product.api.model.ProductPrice;
import com.myretail.product.api.model.ProductURLInfo;
import com.myretail.product.api.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository repository;

	@Override
	public void addProduct(Product productDetail) {
		try {
			ProductPriceEntity entity = new ProductPriceEntity(productDetail.getId(),
					productDetail.getCurrentPrice().getValue(), productDetail.getCurrentPrice().getCurrencyCode());
			repository.insert(entity);

		} catch (Exception e) {
			logger.error("Error when persisting product price detail to database", e);
			throw new ServiceException("Unable to save product into database", e);
		}

	}

	@Override
	public void updateProduct(Product productDetail) {
		try {
			ProductPriceEntity entity = new ProductPriceEntity(productDetail.getId(),
					productDetail.getCurrentPrice().getValue(), productDetail.getCurrentPrice().getCurrencyCode());
			repository.save(entity);
		} catch (Exception e) {
			logger.error("Error when updating product price detail into database", e);
			throw new ServiceException("Unable to update product price into database", e);
		}
	}

	@Override
	public Product getProductByID(long productId, ProductURLInfo urlInfo) {
		ProductPrice productPrice = getProductPrice(productId);
		Product productDetail = new Product(productId, getProductDescription(productId, urlInfo), productPrice);
		return productDetail;
	}

	private ProductPrice getProductPrice(long productId) {
		ProductPrice productPrice = null;
		try {
			Optional<ProductPriceEntity> productPriceEntity = repository.findById(productId);
			productPrice = new ProductPrice(productPriceEntity.get().getPrice(),
					productPriceEntity.get().getCurrency());
		} catch (NoSuchElementException e) {
			logger.error("Unable to find product price detail with given productID: " + productId, e);
			throw new ResourceNotFoundException("Unable to find given product price");
		} catch (Exception e) {
			logger.error("Database error occured when retrieving product price detail for productID: " + productId, e);
			throw new ServiceException("Internal Server error", e);
		}
		return productPrice;
	}

	private String getProductDescription(long productId, ProductURLInfo urlInfo) {

		final String uri = urlInfo.getUrl() + String.valueOf(productId);
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri).queryParam("excludes", urlInfo.getExcludes())
				.queryParam("key", urlInfo.getKey()).build();

		RestTemplate restTemplate = new RestTemplate();
		String productDescription = null;
		String result = null;

		try {
			result = restTemplate.getForObject(builder.toUriString(), String.class);

			JSONObject jsonObject1 = new JSONObject(result).getJSONObject("product");
			JSONObject jsonObject2 = jsonObject1.getJSONObject("item");
			JSONObject jsonObject3 = jsonObject2.getJSONObject("product_description");

			productDescription = jsonObject3.getString("title");
		} catch (HttpClientErrorException e) {
			logger.error("Unable to find product description for given productId: " + productId, e);
			throw new ResourceNotFoundException("Unable to find given product description", e);
		} catch (HttpServerErrorException e) {
			logger.error("Error occurred when making HTTP call to product description API: " + builder.toUriString(),
					e);
			throw new ServiceException("Internal Service error", e);
		} catch (JSONException e) {
			logger.error("Error parsing JSON response returned from product description API: " + result, e);
			throw new ServiceException("Error while parsing JSON from product description API", e);
		}

		// if we found the price but not the product description we should still return
		// null
		if (productDescription == null || productDescription.length() == 0) {
			logger.error("Product description is empty in JSON response from product description API: " + result);
			throw new ResourceNotFoundException("Product description is empty in JSON response");
		}

		return productDescription;
	}

}
