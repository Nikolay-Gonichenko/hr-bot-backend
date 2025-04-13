package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.InstructionType;

/**
 * Репозиторий сущности тип вложений.
 */
@Repository
public interface InstructionTypeRepository extends JpaRepository<InstructionType, Short> {
}
