package com.anderson.populationfeeapi.service;

import com.anderson.populationfeeapi.dto.StateDto;
import com.anderson.populationfeeapi.mapper.StateMapper;
import com.anderson.populationfeeapi.model.State;
import com.anderson.populationfeeapi.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateMapper stateMapper;

    @Transactional
    public List<StateDto> findAll() {
        Iterable<State> states = stateRepository.findAll();
        List<State> stateList = StreamSupport.stream(states.spliterator(), false).collect(Collectors.toList());
        return stateMapper.toDtoList(stateList);
    }
}
