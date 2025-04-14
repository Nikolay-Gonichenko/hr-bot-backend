package ru.itmo.hrbotbackend.service.facade;

import ru.itmo.hrbotbackend.domain.dto.input.AddInstructionsToThemeDto;
import ru.itmo.hrbotbackend.domain.dto.output.Content;

/**
 * 
 */
public interface ContentService {
	
	Content getByThemeId(Long themeId, String userId);

	Boolean addInstructionsToTheme(AddInstructionsToThemeDto dto);
}
