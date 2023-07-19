package com.klagan.pruebaprecios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klagan.pruebaprecios.entity.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Integer> {

}
