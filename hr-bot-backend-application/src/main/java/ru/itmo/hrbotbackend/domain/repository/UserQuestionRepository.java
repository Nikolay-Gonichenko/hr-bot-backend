package ru.itmo.hrbotbackend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.hrbotbackend.domain.entity.UserQuestion;

import java.util.List;

/**
 *
 */
public interface UserQuestionRepository extends JpaRepository<UserQuestion, Long> {

	List<UserQuestion> findAllByAuthorId(Long authorId);

	List<UserQuestion> findAllByAnswerIsNull();
}
