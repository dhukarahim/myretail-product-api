package com.myretail.product.api;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.aop.support.MethodMatchers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.myretail.product.api.entity.ProductPriceEntity;
import com.myretail.product.api.model.ProductDetail;
import com.myretail.product.api.repository.ProductRepository;
import com.myretail.product.api.service.ProductServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.myretail.product.api.*")
@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@MockBean
	private ProductRepository mockRepository;
	
	@Before
	public void init() {
	//	MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Success scenario for retrieving product detail by productID
	 * @throws Exception 
	 */
	@Test
	public void testGetProductID() throws Exception {
		ProductPriceEntity entity = new ProductPriceEntity(12, new BigDecimal(76.45), "USD");
		Optional<ProductPriceEntity> productPriceEntity = Optional.of(entity);
		PowerMockito.when(mockRepository.findById(Long.valueOf(12))).thenReturn(productPriceEntity);
		
		ProductServiceImpl spy = PowerMockito.spy(service);
		//PowerMockito.doReturn("Mobile").when(spy, (ProductServiceImpl.class, "getProductDescription", long.class, Object.class);
		
		ProductDetail actualResult = spy.getProductByID(12, null);
		assertEquals(12, actualResult.getId());
	}

}
