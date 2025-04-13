package ru.itmo.hrbotbackend.rest.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.input.UserBlockDto;
import ru.itmo.hrbotbackend.domain.dto.input.UserCreateDto;
import ru.itmo.hrbotbackend.service.user.UserService;

/**
 * 
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/createOrUpdate")
	public ResponseEntity<Boolean> createOrUpdate(@RequestBody @NotNull UserCreateDto user) {
		return ResponseEntity.ok(userService.createOrUpdateUser(user));
	}

	@PostMapping("/block")
	public ResponseEntity<Boolean> blockUser(@RequestBody @NotNull UserBlockDto userBlockDto) {
		return ResponseEntity.ok(userService.blockUser(userBlockDto));
	}
}
