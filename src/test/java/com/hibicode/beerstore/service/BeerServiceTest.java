package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BeerServiceTest {

    @Test()
    public void should_deny_creation_of_beer_that_exists() {
        var beerService = new BeerService();

        var newBeer = Beer.builder()
                .name("Heineken")
                .type(BeerType.LAGER)
                .volume(new BigDecimal("355"))
                .build();

        Assertions.assertThrows(BeerAlreadyExistException.class, () -> beerService.save(newBeer));
    }
}
