package tech.api.heroapi.rest.controller.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public record HeroResponse(UUID id, String name, String power, String alignment, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
}
