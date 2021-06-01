package dev.feryadi.backend.bayu.repository;

import dev.feryadi.backend.bayu.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {
}
