package com.myretail.product.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.product.api.entity.ProductPriceEntity;

public interface ProductRepository extends MongoRepository<ProductPriceEntity, Long>{

}
