package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Beer;
import com.hibicode.beerstore.model.BeerType;
import com.hibicode.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeerServiceTest {

    private BeerService beerService;

    @BeforeEach
    public void setup() {
        beerService = new BeerService();
    }

    @Test()
    public void should_deny_creation_of_beer_that_exists() {
        var newBeer = Beer.builder()
                .name("Heineken")
                .type(BeerType.LAGER)
                .volume(new BigDecimal("355"))
                .build();

        Assertions.assertThrows(BeerAlreadyExistException.class, () -> beerService.save(newBeer));
    }

    @Test
    public void should_create_new_beer() {
        var newBeer = Beer.builder()
                .name("Heineken")
                .type(BeerType.LAGER)
                .volume(new BigDecimal("355"))
                .build();

        var beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));

    }
}
