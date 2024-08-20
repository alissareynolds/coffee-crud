package com.example.coffee_crud.controller;

import com.example.coffee_crud.exception.CoffeeNotFoundException;
import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.service.CoffeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.coffee_crud.models.CoffeeSize.LARGE;
import static com.example.coffee_crud.models.CoffeeSize.SMALL;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeControllerTest {

    private CoffeeController coffeeController;
    private CoffeeService mockCoffeeService;

    public final Coffee input = new Coffee(null, "Caramel Macchiato", "Almond", 2, LARGE, false, true);
    public final Coffee input2 = new Coffee(null, "Vanilla Latte", "Whole", 1, SMALL, true, false);
    private final Coffee recordWithId = new Coffee(UUID.randomUUID(), "Caramel Macchiato", "Almond", 2, LARGE, false, true);
    private final Coffee recordWithId2 = new Coffee(recordWithId.getId(), "Vanilla Latte", "Whole", 1, SMALL, true, false);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCoffeeService = Mockito.mock(CoffeeService.class);
        coffeeController = new CoffeeController(mockCoffeeService);
    }

    @Test
    public void createCoffee_shouldReturnCoffeeAndCREATEDHttpStatus() {
        Mockito.when(mockCoffeeService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Coffee> response = coffeeController.createCoffee(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllCoffee_shouldReturnListOfCoffeeAndOKHttpStatus() {
        List<Coffee> coffees = new ArrayList<>();
        coffees.add(input);
        coffees.add(input2);
        Mockito.when(mockCoffeeService.getAll()).thenReturn(coffees);
        ResponseEntity<List<Coffee>> response = coffeeController.getAllCoffee();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(coffees, response.getBody());
    }

    @Test
    public void getCoffeeById_shouldReturnCoffeeAndOKHttpStatus() {
        Mockito.when(mockCoffeeService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Coffee> response = coffeeController.getCoffeeById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getCoffeeById_shouldReturn404WhenCoffeeNotFound() {
        Mockito.when(mockCoffeeService.getById(id)).thenThrow(new CoffeeNotFoundException("A coffee with id: " + id + " was not found."));
        ResponseEntity<Coffee> response = coffeeController.getCoffeeById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getCoffeeByName_shouldReturnListOfCoffeeAndOKHttpStatus() {
        Mockito.when(mockCoffeeService.getByName(recordWithId.getName())).thenReturn(List.of(recordWithId));
        ResponseEntity<List<Coffee>> response = coffeeController.getCoffeeByName(recordWithId.getName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(recordWithId), response.getBody());
    }

    @Test
    public void updateCoffee_shouldReturnCoffeeAndOKHttpStatus() {
        Mockito.when(mockCoffeeService.update(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Coffee> response = coffeeController.updateCoffee(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void updateCoffee_shouldReturn404WhenBookNotFound() {
        Mockito.when(mockCoffeeService.update(input, id)).thenThrow(new CoffeeNotFoundException("A coffee with id: " + id + " was not found."));
        ResponseEntity<Coffee> response = coffeeController.updateCoffee(input, id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}