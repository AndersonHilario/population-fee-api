package com.anderson.populationfeeapi.dto;

import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.model.PopulationFee;
import com.anderson.populationfeeapi.model.State;
import com.anderson.populationfeeapi.repository.PopulationFeeRepository;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.MonetaryConversions;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class StateDto {

    @Autowired
    private PopulationFeeRepository populationFeeRepository;

    private String name;
    private Long totalPopulation;
    private Double totalFee;

    public List<StateDto> toDtoList(List<State> state) {
        List<StateDto> dtoList = new ArrayList<>();
        state.forEach(s -> {
            StateDto dto = new StateDto();
            dto.name = s.getName();
            dto.totalPopulation = calculateTotalPopulation(s.getCities());
            dto.totalFee = calculateTotalFee(s.getCities());
        });
        return dtoList;
    }

    private Long calculateTotalPopulation(List<City> cities) {
        AtomicReference<Long> totalPopulation = new AtomicReference<>();
        cities.forEach(city -> totalPopulation.updateAndGet(v -> v + city.getPopulation()));
        return totalPopulation.get();
    }

    private Double calculateTotalFee(List<City> cities) {
        AtomicReference<Double> totalFee = new AtomicReference<>();
        Iterable<PopulationFee> fees = populationFeeRepository.findAll();
        PopulationFee feeConfig = fees.iterator().next();
        MonetaryAmount personalFeeDollar = Monetary.getDefaultAmountFactory().setCurrency("USD").setNumber(feeConfig.getPersonalFee()).create();
        MonetaryAmount feeReal = personalFeeDollar.with(MonetaryConversions.getConversion("BRL"));
        cities.forEach(city -> {

            if (city.getPopulation() > feeConfig.getCutValueDiscount()) {
                long populationWithDiscount = (long) (city.getPopulation() - feeConfig.getCutValueDiscount());
                city.setPopulation(city.getPopulation() - populationWithDiscount);
                MonetaryAmount feeRealWithDiscount = feeReal.multiply(1 - (feeConfig.getDiscountPercentage() / 100));
                double stateFeeWithDiscount = feeRealWithDiscount.multiply(populationWithDiscount).getNumber().doubleValue();
                totalFee.updateAndGet(v -> v + stateFeeWithDiscount);
            }

            double stateFeeWithoutDiscount = feeReal.multiply(city.getPopulation()).getNumber().doubleValue();
            totalFee.updateAndGet(v -> v + stateFeeWithoutDiscount);

        });
        return totalFee.get();
    }

}
