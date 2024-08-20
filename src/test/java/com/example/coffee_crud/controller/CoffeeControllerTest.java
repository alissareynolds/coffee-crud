package com.example.coffee_crud.controller;

import com.example.coffee_crud.exception.CoffeeNotFoundException;
import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.service.CoffeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

}