package tech.api.heroapi.rest.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

public record HeroResponse(UUID id,
                           String name,
                           String power,
                           String alignment,
                           @JsonProperty(value = "created_at")
                           ZonedDateTime createdAt,
                           @JsonProperty(value = "updated_at")
                           ZonedDateTime updatedAt) {
}
