package tech.api.heroapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.api.heroapi.domain.Heroes;

import java.util.UUID;

@Repository
public interface HeroRepository extends JpaRepository<Heroes, UUID> {
}
