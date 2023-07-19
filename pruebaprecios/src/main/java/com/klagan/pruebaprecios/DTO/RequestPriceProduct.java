package com.klagan.pruebaprecios.DTO;

import lombok.Data;

@Data
public class RequestPriceProduct {
    String applicationDate;
    Long productId;
    Long brandId;
}
