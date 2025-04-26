package ru.itmo.hrbotbackend.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.output.LoginDto;
import ru.itmo.hrbotbackend.service.facade.LoginService;

/**
 * Контроллер для входа в систему.
 */
@Tag(name = "Контроллер для входа в систему")
@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	@PostMapping
	public ResponseEntity<LoginDto> login(@RequestBody @NotNull String tgId) {
		return ResponseEntity.ok(loginService.login(tgId));
	}
}
