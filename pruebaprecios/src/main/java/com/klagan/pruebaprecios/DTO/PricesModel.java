package com.klagan.pruebaprecios.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricesModel {

    private Long productId;
    private Long chainId;
    private double rateToApply;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String price;

}
