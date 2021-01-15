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

    public PopulationFee() {
    }

    public PopulationFee(Long id, Double personalFee, Double cutValueDiscount, Double discountPercentage) {
        this.id = id;
        this.personalFee = personalFee;
        this.cutValueDiscount = cutValueDiscount;
        this.discountPercentage = discountPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPersonalFee() {
        return personalFee;
    }

    public void setPersonalFee(Double personalFee) {
        this.personalFee = personalFee;
    }

    public Double getCutValueDiscount() {
        return cutValueDiscount;
    }

    public void setCutValueDiscount(Double cutValueDiscount) {
        this.cutValueDiscount = cutValueDiscount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
