package com.klagan.pruebaprecios.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BRANDS")
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    @Getter
    private Long brandId;

    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;

    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;
}
