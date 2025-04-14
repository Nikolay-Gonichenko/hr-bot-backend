package ru.itmo.hrbotbackend.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.input.AnswerDto;
import ru.itmo.hrbotbackend.domain.dto.input.CreateUserQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.input.GetMyQuestionDto;
import ru.itmo.hrbotbackend.domain.dto.output.QuestionForAnswerDto;
import ru.itmo.hrbotbackend.domain.dto.output.UserQuestionOutDto;
import ru.itmo.hrbotbackend.service.faq.UserQuestionService;

import java.util.List;

/**
 * 
 */
@Tag(name = "Контроллер для вопросов пользователей.")
@RestController
@RequestMapping("faq")
@RequiredArgsConstructor
public class UserQuestionController {

	private final UserQuestionService userQuestionService;

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody @NotNull CreateUserQuestionDto createDto) {
		return ResponseEntity.ok(userQuestionService.create(createDto));
	}

	@GetMapping("/getForAnswer")
	public ResponseEntity<List<QuestionForAnswerDto>> getForAnswer() {
		return ResponseEntity.ok(userQuestionService.getQuestionForAnswer());
	}

	@PostMapping("/getAllMy")
	public ResponseEntity<List<UserQuestionOutDto>> getAllMy(@RequestBody @NotNull GetMyQuestionDto getMyQuestionDto) {
		return ResponseEntity.ok(userQuestionService.getAllMy(getMyQuestionDto));
	}

	@PostMapping("/answer")
	public ResponseEntity<Boolean> answer(@RequestBody @NotNull AnswerDto answerDto) {
		return ResponseEntity.ok(userQuestionService.answer(answerDto));
	}
}
