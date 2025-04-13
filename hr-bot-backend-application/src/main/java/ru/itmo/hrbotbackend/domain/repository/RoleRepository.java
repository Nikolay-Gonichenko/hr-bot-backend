package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.Role;

/**
 * Репозиторий сущности уровня доступа пользователя.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
}
