package com.example.coffee_crud.service;

import com.example.coffee_crud.exception.CoffeeNotFoundException;
import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.repository.CoffeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.coffee_crud.models.CoffeeSize.LARGE;
import static com.example.coffee_crud.models.CoffeeSize.SMALL;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeServiceTest {

    private CoffeeService coffeeService;
    private CoffeeRepository mockCoffeeRepository;

    public final Coffee input = new Coffee(null, "Caramel Macchiato", "Almond", 2, LARGE, false, true);
    public final Coffee input2 = new Coffee(null, "Vanilla Latte", "Whole", 1, SMALL, true, false);
    private final Coffee recordWithId = new Coffee(UUID.randomUUID(), "Caramel Macchiato", "Almond", 2, LARGE, false, true);
    private final Coffee recordWithId2 = new Coffee(recordWithId.getId(), "Vanilla Latte", "Whole", 1, SMALL, true, false);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCoffeeRepository = Mockito.mock(CoffeeRepository.class);
        coffeeService = new CoffeeService(mockCoffeeRepository);
    }

    @Test
    public void create_shouldReturnCreatedCoffee() {
        Mockito.when(mockCoffeeRepository.save(Mockito.any())).thenReturn(recordWithId);
        Coffee response = coffeeService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfCoffee() {
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(input);
        coffees.add(input2);
        Mockito.when(mockCoffeeRepository.findAll()).thenReturn(coffees);
        List<Coffee> response = coffeeService.getAll();
        assertEquals(coffees, response);
    }

    @Test
    public void getById_shouldReturnCoffee() {
        Mockito.when(mockCoffeeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Coffee response = coffeeService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenCoffeeWasNotFound() {
        Mockito.when(mockCoffeeRepository.findById(id)).thenReturn(Optional.empty());
        CoffeeNotFoundException exception = assertThrows(CoffeeNotFoundException.class, () -> coffeeService.getById(id));
        assertEquals("A coffee with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByName_shouldReturnListOfCoffee() {
        Mockito.when(mockCoffeeRepository.findByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        List<Coffee> response = coffeeService.getByName(recordWithId.getName());
        assertEquals(List.of(recordWithId), response);
    }

}