package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.Position;

/**
 * Репозиторий сущности должности работника.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
