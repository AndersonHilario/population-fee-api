package com.anderson.populationfeeapi.mapper;

import com.anderson.populationfeeapi.dto.StateDto;
import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.model.PopulationFee;
import com.anderson.populationfeeapi.model.State;
import com.anderson.populationfeeapi.repository.CityRepository;
import com.anderson.populationfeeapi.repository.PopulationFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.MonetaryConversions;
import java.util.ArrayList;
import java.util.List;

@Component
public class StateMapper {

    @Autowired
    private PopulationFeeRepository populationFeeRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<StateDto> toDtoList(List<State> state) {
        List<StateDto> dtoList = new ArrayList<>();
        state.forEach(s -> {
            List<City> cities = cityRepository.findByStateId(s.getId());
            StateDto dto = new StateDto();
            dto.setName(s.getName());
            dto.setTotalPopulation(calculateTotalPopulation(cities));
            dto.setTotalFee(calculateTotalFee(cities));
            dto.setCities(cities);
            dtoList.add(dto);
        });
        return dtoList;
    }

    private Long calculateTotalPopulation(List<City> cities) {
        Long totalPopulation = 0L;
        for (City city : cities) {
            totalPopulation += city.getPopulation();
        }
        return totalPopulation;
    }

    private Double calculateTotalFee(List<City> cities) {
        double totalFee = 0D;
        Iterable<PopulationFee> fees = populationFeeRepository.findAll();
        PopulationFee feeConfig = fees.iterator().next();
        MonetaryAmount personalFeeDollar = Monetary.getDefaultAmountFactory().setCurrency("USD").setNumber(feeConfig.getPersonalFee()).create();
        MonetaryAmount feeReal = personalFeeDollar.with(MonetaryConversions.getConversion("BRL"));
        for (City city : cities) {

            long populationWithDiscount = (long) (city.getPopulation() - feeConfig.getCutValueDiscount());
            long populationWithoutDiscount = city.getPopulation() - populationWithDiscount;

            if (populationWithDiscount > 0) {
                MonetaryAmount feeRealWithDiscount = feeReal.multiply(1 - (feeConfig.getDiscountPercentage() / 100));
                double stateFeeWithDiscount = feeRealWithDiscount.multiply(populationWithDiscount).getNumber().doubleValue();
                totalFee += totalFee + stateFeeWithDiscount;
            }

            double stateFeeWithoutDiscount = feeReal.multiply(populationWithoutDiscount).getNumber().doubleValue();
            totalFee += totalFee + stateFeeWithoutDiscount;

        }
        return totalFee;
    }
}
