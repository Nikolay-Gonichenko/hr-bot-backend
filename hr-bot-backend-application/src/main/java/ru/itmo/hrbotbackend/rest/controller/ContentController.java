package ru.itmo.hrbotbackend.rest.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.hrbotbackend.domain.dto.input.AddInstructionsToThemeDto;
import ru.itmo.hrbotbackend.domain.dto.output.Content;
import ru.itmo.hrbotbackend.service.facade.ContentService;

/**
 * Контроллер контента.
 */
@Tag(name = "Контроллер контента")
@RestController
@RequestMapping("content")
@RequiredArgsConstructor
public class ContentController {

	private final ContentService contentService;

	@GetMapping("/get")
	public ResponseEntity<Content> get(@RequestParam(value = "themeId", required = false) Long themeId,
									   @RequestParam("tgId") String userId) {
		return ResponseEntity.ok(contentService.getByThemeId(themeId, userId));
	}

	@PostMapping("/addInstructionToTheme")
	public ResponseEntity<Boolean> addInstructionsToTheme(@RequestBody AddInstructionsToThemeDto dto) {
		return ResponseEntity.ok(contentService.addInstructionsToTheme(dto));
	}
}
