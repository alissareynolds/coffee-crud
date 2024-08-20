package com.example.coffee_crud.service;

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

    @BeforeEach
    public void setup() {
        mockCoffeeRepository = Mockito.mock(CoffeeRepository.class);
        coffeeService = new CoffeeService(mockCoffeeRepository);
    }

}