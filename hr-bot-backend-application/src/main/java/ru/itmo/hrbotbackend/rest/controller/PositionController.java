package ru.itmo.hrbotbackend.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.common.PositionDto;
import ru.itmo.hrbotbackend.service.position.PositionService;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Контроллер для работы с должностями.
 */
@Tag(name = "Контроллер для работы с должностями")
@RestController
@RequestMapping("position")
@RequiredArgsConstructor
public class PositionController {

	private final PositionService positionService;

	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody @NotNull String name) {
		return ResponseEntity.ok(positionService.create(name));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PositionDto>> getAll() {
		return ResponseEntity.ok(positionService.getAll());
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody @NotNull PositionDto positionDto) {
		return ResponseEntity.ok(positionService.update(positionDto));
	}

	@PostMapping("/delete")
	public ResponseEntity<Boolean> delete(@RequestBody @NotNull Long id) {
		return ResponseEntity.ok(positionService.deleteById(id));
	}
}
