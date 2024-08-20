package com.example.coffee_crud.service;

import com.example.coffee_crud.exception.CoffeeNotFoundException;
import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.repository.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CoffeeService {

    private CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public Coffee create(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public List<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    public Coffee getById(UUID id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isEmpty()) {
            throw new CoffeeNotFoundException("A coffee with id: " + id + " was not found.");
        }
        return optionalCoffee.get();
    }

    public List<Coffee> getByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public Coffee update(Coffee coffee, UUID id) {
        Optional<Coffee> originalCoffee = coffeeRepository.findById(id);
        if (originalCoffee.isEmpty()) {
            throw new CoffeeNotFoundException("A coffee with id: " + id + " was not found.");
        }
        coffee.setId(id);
        return coffeeRepository.save(coffee);
    }

    public Coffee patch(Coffee coffee, UUID id) {
        Optional<Coffee> originalCoffee = coffeeRepository.findById(id);
        if (originalCoffee.isEmpty()) {
            throw new CoffeeNotFoundException("A coffee with id: " + id + " was not found.");
        }
        Coffee updatedCoffee = originalCoffee.get();
        if (coffee.getName() != null) {
            updatedCoffee.setName(coffee.getName());
        }
        if (coffee.getMilk() != null) {
            updatedCoffee.setMilk(coffee.getMilk());
        }
        if (coffee.getShots() != null) {
            updatedCoffee.setShots(coffee.getShots());
        }
        if (coffee.getSize() != null) {
            updatedCoffee.setSize(coffee.getSize());
        }
        if (coffee.getIsHot() != null) {
            updatedCoffee.setIsHot(coffee.getIsHot());
        }
        if (coffee.getIsIced() != null) {
            updatedCoffee.setIsIced(coffee.getIsIced());
        }
        return coffeeRepository.save(updatedCoffee);
    }

    public void delete(UUID id) {
        coffeeRepository.deleteById(id);
    }
}
