package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itmo.hrbotbackend.domain.entity.Theme;

import java.util.List;

/**
 * Репозиторий сущности раздел.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

	@Query("select t from Theme t where t.parent.id = :parentId")
	List<Theme> findAllByParentId(Long parentId);

	List<Theme> findAllByParentIsNull();
}
