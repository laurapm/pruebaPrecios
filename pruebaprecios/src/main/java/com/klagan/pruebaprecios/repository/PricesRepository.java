package com.klagan.pruebaprecios.repository;

import com.klagan.pruebaprecios.entity.Price;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PricesRepository extends JpaRepository<Price, Long> {

    Optional<Price> findById(Long id);

    List<Price> findByBrandBrandIdAndProductProductId(Long brandId, Long productId);

    List<Price> findByBrandBrandId(Long brandID);

    List<Price> findByProductProductId(Long productId);

}
