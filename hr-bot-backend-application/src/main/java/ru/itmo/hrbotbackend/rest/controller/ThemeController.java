package ru.itmo.hrbotbackend.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.input.ThemeCreateDto;
import ru.itmo.hrbotbackend.domain.dto.input.UpdateThemeDto;
import ru.itmo.hrbotbackend.service.theme.ThemeService;

/**
 * Контроллер для работы с темами и подтемами.
 */
@Tag(name = "Контроллер для работы с темами и подтемами.")
@RestController
@RequestMapping("theme")
@RequiredArgsConstructor
public class ThemeController {
	
	private final ThemeService themeService;
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody @NotNull ThemeCreateDto createDto) {
		return ResponseEntity.ok(themeService.createTheme(createDto));
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody @NotNull UpdateThemeDto updateThemeDto) {
		return ResponseEntity.ok(themeService.updateTheme(updateThemeDto));
	}

	@PostMapping("/deleteById")
	public ResponseEntity<Boolean> deleteById(@RequestBody @NotNull Long themeId) {
		return ResponseEntity.ok(themeService.deleteById(themeId));
	}

	@PostMapping("/deleteByParentId")
	public ResponseEntity<Boolean> deleteByParentId(@RequestBody @NotNull Long parentId) {
		return ResponseEntity.ok(themeService.deleteByParentId(parentId));
	}
}
