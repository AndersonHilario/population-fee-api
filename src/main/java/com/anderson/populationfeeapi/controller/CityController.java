package com.anderson.populationfeeapi.controller;

import com.anderson.populationfeeapi.model.City;
import com.anderson.populationfeeapi.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "city")
public class CityController {

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Create a city", notes = "Create a city by json data")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        try {
            return ResponseEntity.ok().body(cityService.save(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @ApiOperation(value = "Delete city", notes = "Delete city by id in parameter")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            cityService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}
