package tech.api.heroapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.api.heroapi.rest.controller.model.HeroRequest;
import tech.api.heroapi.service.HeroService;

import java.util.UUID;

@RestController
public class HeroController {

    @Autowired
    private HeroService heroService;

    @PostMapping
    ResponseEntity<?> createHero(@RequestBody HeroRequest heroRequest) {
        heroService.startCreateHero(heroRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    ResponseEntity<?> createHero() {
        var hero = heroService.getHero(UUID.fromString("1d5a2d5c-01a3-4927-9aef-157eca6a2af1"));

        return ResponseEntity.status(HttpStatus.OK).body(hero);
    }
}
