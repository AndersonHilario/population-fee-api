package com.anderson.populationfeeapi.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@Table
@ApiModel(description = "Population Fee Entity")
public class PopulationFee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Double personalFee;

    @Column(nullable = false)
    private Double cutValueDiscount;

    @Column(nullable = false)
    private Double discountPercentage;
}
