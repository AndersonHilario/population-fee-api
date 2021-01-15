package com.anderson.populationfeeapi.dto;

import com.anderson.populationfeeapi.model.City;

import java.util.List;

public class StateDto {

    private String name;
    private Long totalPopulation;
    private Double totalFee;
    private List<City> cities;

    public StateDto() {
    }

    public StateDto(String name, Long totalPopulation, Double totalFee, List<City> cities) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.totalFee = totalFee;
        this.cities = cities;
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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
