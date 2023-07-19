package com.klagan.pruebaprecios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klagan.pruebaprecios.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
