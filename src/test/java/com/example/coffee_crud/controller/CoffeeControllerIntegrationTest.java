package com.example.coffee_crud.controller;

import com.example.coffee_crud.models.Coffee;
import com.example.coffee_crud.models.CoffeeSize;
import com.example.coffee_crud.service.CoffeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CoffeeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CoffeeService mockCoffeeService;

    private final Coffee coffee = new Coffee(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"), "Caramel Macchiato", "whole", 2, CoffeeSize.LARGE, false, true);

    @Test
    public void createCoffee() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/coffee")
                .content(asJsonString(new Coffee(null, "Caramel Macchiato", "whole", 2, CoffeeSize.LARGE, false, true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCoffee() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/coffee")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void getCoffeeById() throws Exception {
        Mockito.when(mockCoffeeService.getById(UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810"))).thenReturn(new Coffee());
        mvc.perform(MockMvcRequestBuilders
                .get("/api/coffee/59c47568-fde0-4dd7-9aef-03db6a962810").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
