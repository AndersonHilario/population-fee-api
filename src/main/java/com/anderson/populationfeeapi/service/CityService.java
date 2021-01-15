package com.anderson.populationfeeapi.service;

import com.anderson.populationfeeapi.exception.CityIsFromRioGrandeDoSulException;
import com.anderson.populationfeeapi.exception.CityNameAlreadyCreatedInStateException;
import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public City save(City city) {
        if (cityRepository.existsByNameIgnoreCaseAndStateId(city.getName(), city.getState().getId())) {
            throw new CityNameAlreadyCreatedInStateException("The name city is already in use to this state");
        }
        return cityRepository.save(city);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);

        if (!optionalCity.isPresent()) {
            throw new NoSuchElementException(String.format("id : %o is invalid", id));
        }

        City city = optionalCity.get();
        if (Objects.nonNull(city.getState()) && city.getState().getName().equalsIgnoreCase("Rio Grande do Sul")) {
            throw new CityIsFromRioGrandeDoSulException("Cities from Rio Grande do Sul cannot be deleted");
        }

        cityRepository.deleteById(id);
    }

}
