package com.anderson.populationfeeapi.service;

import com.anderson.populationfeeapi.dto.StateDto;
import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.model.State;
import com.anderson.populationfeeapi.repository.CityRepository;
import com.anderson.populationfeeapi.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateDto stateDto;

    @Transactional
    public List<StateDto> findAll() {
        Iterable<State> states = stateRepository.findAll();
        List<State> stateList = StreamSupport.stream(states.spliterator(), false).collect(Collectors.toList());
        return stateDto.toDtoList(stateList);
    }
}
