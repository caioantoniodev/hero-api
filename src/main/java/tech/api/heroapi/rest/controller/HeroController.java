package tech.api.heroapi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    ResponseEntity<?> createHero(@PathVariable String id) {
        var heroResponse = heroService.getHero(UUID.fromString(id));

        return ResponseEntity.status(HttpStatus.OK).body(heroResponse);
    }
}
