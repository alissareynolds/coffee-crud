package com.example.coffee_crud.controller;

import com.example.coffee_crud.exception.CoffeeNotFoundException;
import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee newCoffee = coffeeService.create(coffee);
        return new ResponseEntity<>(newCoffee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffee() {
        List<Coffee> coffees = coffeeService.getAll();
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable UUID id) {
        Coffee coffee;
        try {
            coffee = coffeeService.getById(id);
        } catch (CoffeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Coffee>> getCoffeeByName(@PathVariable String name) {
       List<Coffee> coffees = coffeeService.getByName(name);
       return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@RequestBody Coffee coffee, @PathVariable UUID id) {
        Coffee newCoffee;
        try {
            newCoffee = coffeeService.update(coffee, id);
        } catch (CoffeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCoffee, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Coffee> patchCoffee(@RequestBody Coffee coffee, @PathVariable UUID id) {
        Coffee newCoffee;
        try {
            newCoffee = coffeeService.patch(coffee, id);
        } catch (CoffeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCoffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Coffee> deleteCoffee(@PathVariable UUID id) {
        coffeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
