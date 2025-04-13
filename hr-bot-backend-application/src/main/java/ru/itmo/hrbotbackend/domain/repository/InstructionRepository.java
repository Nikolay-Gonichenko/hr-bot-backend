package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.Instruction;

import java.util.List;

/**
 * Репозиторий сущности вложение.
 */
@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

	@Query("select i from Instruction i where i.theme.id = :themeId")
	List<Instruction> findAllByThemeId(Long themeId);

	@Modifying
	void deleteAllByThemeId(Long themeId);
}
