package com.klagan.pruebaprecios.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.klagan.pruebaprecios.entity.Price;

public interface PriceService {
    Optional<Price> calculateFinalPrice(LocalDateTime applicationDate, Long productID, Long brandID);
}
