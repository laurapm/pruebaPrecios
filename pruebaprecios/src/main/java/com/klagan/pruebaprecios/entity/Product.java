package com.klagan.pruebaprecios.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public Product(Long productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
