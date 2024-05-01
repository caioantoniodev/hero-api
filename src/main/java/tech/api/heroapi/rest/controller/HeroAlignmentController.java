package tech.api.heroapi.rest.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.api.heroapi.domain.enums.HeroAlignmentEnum;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/heroes/alignment")
public class HeroAlignmentController {

    @GetMapping
    public ResponseEntity<?> getAlignments() {
        var heroAlignmentEnums = Arrays.asList(HeroAlignmentEnum.values());
        return ResponseEntity.ok(heroAlignmentEnums);
    }
}
