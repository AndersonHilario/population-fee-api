package com.anderson.populationfeeapi.dto;

public class StateDto {

    private String name;
    private Long totalPopulation;
    private Double totalFee;

    public StateDto() {
    }

    public StateDto(String name, Long totalPopulation, Double totalFee) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.totalFee = totalFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }
}
