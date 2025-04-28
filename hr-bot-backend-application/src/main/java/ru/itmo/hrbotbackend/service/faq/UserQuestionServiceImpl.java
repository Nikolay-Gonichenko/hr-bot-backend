package ru.itmo.hrbotbackend.service.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.input.AnswerDto;
import ru.itmo.hrbotbackend.domain.dto.input.CreateUserQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.input.GetMyQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.output.QuestionForAnswerDto;
import ru.itmo.hrbotbackend.domain.dto.output.UserQuestionOutDto;
import ru.itmo.hrbotbackend.domain.entity.User;
import ru.itmo.hrbotbackend.domain.entity.UserQuestion;
import ru.itmo.hrbotbackend.domain.repository.UserQuestionRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;
import ru.itmo.hrbotbackend.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class UserQuestionServiceImpl implements UserQuestionService {

	private final UserQuestionRepository userQuestionRepository;
	private final UserService userService;

	@Override
	@Transactional
	public boolean create(CreateUserQuestionDto createUserQuestionDto) {
		var authorTgId = createUserQuestionDto.getTgId();
		var user = this.getUserByTgId(authorTgId);
		var entity = UserQuestion
				.builder()
				.question(createUserQuestionDto.getQuestion())
				.author(user)
				.build();
		userQuestionRepository.save(entity);
		return true;
	}

	@Override
	public List<UserQuestionOutDto> getAllMy(GetMyQuestionDto getMyQuestionDto) {
		var answered = getMyQuestionDto.getAnswered();
		var authorTgId = getMyQuestionDto.getTgId();
		var user = this.getUserByTgId(authorTgId);
		List<UserQuestion> userQuestions = userQuestionRepository.findAllByAuthorId(user.getId());
		if (answered != null) {
			userQuestions = userQuestions.stream()
					.filter(userQuestion -> answered == (userQuestion.getAnswer() != null))
					.collect(Collectors.toList());
		}
		return userQuestions.stream()
				.map(userQuestion -> UserQuestionOutDto
						.builder()
						.answer(userQuestion.getAnswer())
						.question(userQuestion.getQuestion())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public List<QuestionForAnswerDto> getQuestionForAnswer(String adminId) {
		var admin = this.getUserByTgId(adminId);
		return userQuestionRepository.findAllByAnswerIsNull()
				.stream()
				.filter(userQuestion -> !userQuestion.getAuthor().equals(admin))
				.map(userQuestion -> QuestionForAnswerDto
						.builder()
						.id(userQuestion.getId())
						.question(userQuestion.getQuestion())
						.build()
				)
				.collect(Collectors.toList());
	}

	@Override
	public boolean answer(AnswerDto answerDto) {
		var user = this.getUserByTgId(answerDto.getResponderId());
		var userQuestion = userQuestionRepository.findById(answerDto.getQuestionId())
				.orElseThrow(() -> new BaseException(ExceptionCode.USER_QUESTION_NOT_FOUND_BY_ID, answerDto.getQuestionId()));
		userQuestion.setAnswer(answerDto.getAnswer());
		userQuestion.setResponder(user);
		userQuestionRepository.save(userQuestion);
		return true;
	}
	
	private User getUserByTgId(String tgId) {
		return userService.getByTgId(tgId)
				.orElseThrow(() -> new BaseException(ExceptionCode.USER_NOT_FOUND_BY_TG_ID, tgId));
	}
}
