package ru.itmo.hrbotbackend.service.faq;

import ru.itmo.hrbotbackend.domain.dto.input.AnswerDto;
import ru.itmo.hrbotbackend.domain.dto.input.CreateUserQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.input.GetMyQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.output.QuestionForAnswerDto;
import ru.itmo.hrbotbackend.domain.dto.output.UserQuestionOutDto;

import java.util.List;

/**
 *
 */
public interface UserQuestionService {

	boolean create(CreateUserQuestionDto createUserQuestionDto);

	List<UserQuestionOutDto> getAllMy(GetMyQuestionDto getMyQuestionDto);

	List<QuestionForAnswerDto> getQuestionForAnswer(String adminId);

	 boolean answer(AnswerDto answerDto);
}
