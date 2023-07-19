package com.klagan.pruebaprecios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klagan.pruebaprecios.entity.Product;
import com.klagan.pruebaprecios.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product addProduct() {
        Product productToadd = new Product(35455L, "camiseta", "rebajas");
        productRepository.saveAndFlush(productToadd);

        return productToadd;
    }
}
