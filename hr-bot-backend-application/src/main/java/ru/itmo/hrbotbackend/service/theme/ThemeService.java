package ru.itmo.hrbotbackend.service.theme;

import ru.itmo.hrbotbackend.domain.dto.input.ThemeCreateDto;
import ru.itmo.hrbotbackend.domain.dto.input.UpdateThemeDto;
import ru.itmo.hrbotbackend.domain.dto.internal.ThemeDto;
import ru.itmo.hrbotbackend.domain.entity.Theme;

import java.util.List;

/**
 * 
 */
public interface ThemeService {
	
	List<ThemeDto> getByParentId(Long parentId);

	Long createTheme(ThemeCreateDto createDto);

	boolean updateTheme(UpdateThemeDto updateThemeDto);

	boolean deleteById(Long themeId);

	boolean deleteByParentId(Long parentId);

	Theme getById(Long id);
}
