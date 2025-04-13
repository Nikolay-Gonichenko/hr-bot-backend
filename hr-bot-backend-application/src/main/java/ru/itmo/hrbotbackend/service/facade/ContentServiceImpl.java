package ru.itmo.hrbotbackend.service.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.input.AddInstructionsToThemeDto;
import ru.itmo.hrbotbackend.domain.dto.internal.InstructionCreateDto;
import ru.itmo.hrbotbackend.domain.dto.output.Content;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;
import ru.itmo.hrbotbackend.service.instruction.InstructionService;
import ru.itmo.hrbotbackend.service.theme.ThemeService;
import ru.itmo.hrbotbackend.service.user.UserService;

import java.util.stream.Collectors;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
	
	private final ThemeService themeService;
	private final InstructionService instructionService;
	private final UserService userService;
	
	@Override
	@Transactional
	public Content getByThemeId(Long themeId) {
		var content = Content.builder();
		var children = themeService.getByParentId(themeId);
		if (children.isEmpty()) {
			content
					.description(themeService.getDescriptionById(themeId))
					.instructions(instructionService.getByThemeId(themeId))
					.leaf(true);
		} else {
			content
					.children(children)
					.leaf(false);
		}
		return content.build();
	}

	@Override
	@Transactional
	public Boolean addInstructionsToTheme(AddInstructionsToThemeDto dto) {
		var authorTgId = dto.getAuthorTgId();
		var author = userService.getByTgId(authorTgId)
				.orElseThrow(() -> new BaseException(ExceptionCode.USER_NOT_FOUND_BY_TG_ID, authorTgId));
		var themeId = dto.getThemeId();
		var theme = themeService.getById(themeId);
		if (!theme.getChildren().isEmpty()) {
			throw new BaseException(ExceptionCode.THEME_IS_NOT_LEAF, theme.getThemeName());
		}
		instructionService.deleteByThemeId(themeId);
		instructionService.create(dto.getInstructions()
				.stream()
				.map(inputDto ->
						InstructionCreateDto
								.builder()
								.theme(theme)
								.author(author)
								.content(inputDto.getContent())
								.typeId(inputDto.getTypeId())
								.build()
				).collect(Collectors.toList()));
		return true;
	}
}
