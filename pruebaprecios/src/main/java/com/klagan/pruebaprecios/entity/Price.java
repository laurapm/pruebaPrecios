package com.klagan.pruebaprecios.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICE_ID")
    @Getter
    private Long priceId;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    @Getter
    @Setter
    private Brand brand;

    @Column(name = "START_DATE")
    @Getter
    @Setter
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    @Getter
    @Setter
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "PRICE_LIST")
    @Getter
    @Setter
    private PriceList priceList;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @Getter
    @Setter
    private Product product;

    @Column(name = "PRIORITY")
    @Getter
    @Setter
    private int priority;

    @Column(name = "PRICE")
    @Getter
    @Setter
    private double price;

    @Column(name = "CURR")
    @Getter
    @Setter
    private String currency;

}
