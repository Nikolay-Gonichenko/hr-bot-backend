package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.User;

import java.util.Optional;

/**
 * Репозиторий сущности пользователь.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByTgId(String tgId);
}
