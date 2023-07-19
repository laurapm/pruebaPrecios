package com.klagan.pruebaprecios;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.klagan.pruebaprecios.entity.Brand;
import com.klagan.pruebaprecios.entity.Price;
import com.klagan.pruebaprecios.entity.PriceList;
import com.klagan.pruebaprecios.entity.Product;
import com.klagan.pruebaprecios.repository.PricesRepository;
import com.klagan.pruebaprecios.service.implementation.PriceServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PruebapreciosApplicationTests {

	@Mock
	private PricesRepository pricesRepository;

	@InjectMocks
	private PriceServiceImpl priceService;

	private Price price0;
	private Price price2;
	private Price price3;
	private Price price4;

	private Product product;

	private Brand brand;

	private PriceList priceList1;
	private PriceList priceList2;
	private PriceList priceList3;
	private PriceList priceList4;

	@BeforeEach
	public void setUp() {
		product = new Product(35455L, "blusas", "coleccion verano 2023");
		brand = new Brand(1L, "ZARA", "GRUPO INDITEX");

		priceList1 = new PriceList(1, product);
		priceList2 = new PriceList(2, product);
		priceList3 = new PriceList(3, product);
		priceList4 = new PriceList(4, product);

		price0 = new Price(0L, brand, LocalDateTime.of(2020, 6, 14, 00, 0), LocalDateTime.of(2020, 12, 31, 23, 59),
				priceList1, product, 0, 35.50, "EUR");
		price2 = new Price(2L, brand, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 06, 14, 18, 30),
				priceList2, product, 1, 25.45, "EUR");
		price3 = new Price(3L, brand, LocalDateTime.of(2020, 6, 15, 00, 0), LocalDateTime.of(2020, 06, 15, 11, 00),
				priceList3, product, 1, 30.50, "EUR");
		price4 = new Price(4L, brand, LocalDateTime.of(2020, 6, 15, 16, 0), LocalDateTime.of(2020, 12, 31, 23, 59),
				priceList4, product, 1, 38.95, "EUR");

	}

	@Test
	@DisplayName("Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)")
	public void test1() {
		// data For test
		Long productIdTest = 35455L;
		Long brandIdTest = 1L;
		LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 10, 00);

		// Precondition
		when(pricesRepository.findByBrandBrandIdAndProductProductId(brandIdTest, productIdTest))
				.thenReturn(List.of(price0, price2, price3, price4));

		// Action to test
		Optional<Price> result = priceService.calculateFinalPrice(dateTime, productIdTest, brandIdTest);

		// Verifications
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getBrand().getBrandId());
		assertEquals(35455L, result.get().getProduct().getProductId());
		assertEquals(0, result.get().getPriority());
		assertEquals(35.50, result.get().getPrice());
	}

	@Test
	@DisplayName("Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ)")
	public void test2() {
		// data For test
		Long productIdTest = 35455L;
		Long brandIdTest = 1L;
		LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 16, 00);

		// Precondition
		when(pricesRepository.findByBrandBrandIdAndProductProductId(brandIdTest, productIdTest))
				.thenReturn(List.of(price0, price2, price3, price4));

		// Action to test
		Optional<Price> result = priceService.calculateFinalPrice(dateTime, productIdTest, brandIdTest);

		// Verifications
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getBrand().getBrandId());
		assertEquals(35455L, result.get().getProduct().getProductId());
		assertEquals(1, result.get().getPriority());
		assertEquals(25.45, result.get().getPrice());
	}

	@Test
	@DisplayName("Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ)")
	public void test3() {
		// data For test
		Long productIdTest = 35455L;
		Long brandIdTest = 1L;
		LocalDateTime dateTime = LocalDateTime.of(2020, 6, 14, 21, 00);

		// Precondition
		when(pricesRepository.findByBrandBrandIdAndProductProductId(brandIdTest, productIdTest))
				.thenReturn(List.of(price0, price2, price3, price4));

		// Action to test
		Optional<Price> result = priceService.calculateFinalPrice(dateTime, productIdTest, brandIdTest);

		// Verifications
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getBrand().getBrandId());
		assertEquals(35455L, result.get().getProduct().getProductId());
		assertEquals(0, result.get().getPriority());
		assertEquals(35.50, result.get().getPrice());
	}

	@Test
	@DisplayName("Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)")
	public void test4() {
		// data For test
		Long productIdTest = 35455L;
		Long brandIdTest = 1L;
		LocalDateTime dateTime = LocalDateTime.of(2020, 6, 15, 10, 00);

		// Precondition
		when(pricesRepository.findByBrandBrandIdAndProductProductId(brandIdTest, productIdTest))
				.thenReturn(List.of(price0, price2, price3, price4));

		// Action to test
		Optional<Price> result = priceService.calculateFinalPrice(dateTime, productIdTest, brandIdTest);

		// Verifications
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getBrand().getBrandId());
		assertEquals(35455L, result.get().getProduct().getProductId());
		assertEquals(1, result.get().getPriority());
		assertEquals(30.50, result.get().getPrice());
	}

	@Test
	@DisplayName("Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)")
	public void test5() {
		// data For test
		Long productIdTest = 35455L;
		Long brandIdTest = 1L;
		LocalDateTime dateTime = LocalDateTime.of(2020, 6, 16, 21, 00);

		// Precondition
		when(pricesRepository.findByBrandBrandIdAndProductProductId(brandIdTest, productIdTest))
				.thenReturn(List.of(price0, price2, price3, price4));

		// Action to test
		Optional<Price> result = priceService.calculateFinalPrice(dateTime, productIdTest, brandIdTest);

		// Verifications
		assertNotNull(result);
		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getBrand().getBrandId());
		assertEquals(35455L, result.get().getProduct().getProductId());
		assertEquals(1, result.get().getPriority());
		assertEquals(38.95, result.get().getPrice());
	}

}
