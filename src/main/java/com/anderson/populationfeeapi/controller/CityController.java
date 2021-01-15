package com.anderson.populationfeeapi.controller;

import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return null;
    }
}
