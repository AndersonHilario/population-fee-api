package com.anderson.populationfeeapi.repository;

import com.anderson.populationfeeapi.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findByStateId(Long stateId);

    boolean existsByNameIgnoreCaseAndStateId(String name, Long stateid);

}
