package com.klagan.pruebaprecios.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRICE_LIST")
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRICE_LIST_ID")
    @Getter
    @Setter
    private Integer priceListId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    @Getter
    @Setter
    private Product product;

}
