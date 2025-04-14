package ru.itmo.hrbotbackend.service.theme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.input.ThemeCreateDto;
import ru.itmo.hrbotbackend.domain.dto.input.UpdateThemeDto;
import ru.itmo.hrbotbackend.domain.dto.internal.ThemeDto;
import ru.itmo.hrbotbackend.domain.entity.Theme;
import ru.itmo.hrbotbackend.domain.repository.ThemeRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;

import java.util.List;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
	
	private final ThemeRepository repository;
	private final ThemeMapper mapper;
	
	@Override
	public List<ThemeDto> getByParentId(Long parentId) {
		return mapper.mapListToDto(this.findAllByParentId(parentId));
	}

	@Override
	@Transactional
	public Long createTheme(ThemeCreateDto createDto) {
		var parentId = createDto.getParentId();
		var parent = parentId != null ? this.getById(parentId) : null;
		var theme = Theme
				.builder()
				.themeName(createDto.getThemeName())
				.description(createDto.getDescription())
				.parent(parent)
				.accessLevel(createDto.getAccessLevel())
				.build();
		var saved = repository.save(theme);
		return saved.getId();
	}

	@Override
	public boolean updateTheme(UpdateThemeDto updateThemeDto) {
		var entity = this.getById(updateThemeDto.getId());
		var parentId = updateThemeDto.getParentId();
		var parentEntity = entity.getParent();
		if (parentEntity != null && !entity.getParent().getId().equals(parentId)) {
			var newParent = this.getById(parentId);
			entity.setParent(newParent);
		}
		entity.setThemeName(updateThemeDto.getThemeName());
		entity.setDescription(updateThemeDto.getDescription());
		entity.setAccessLevel(updateThemeDto.getAccessLevel());
		repository.save(entity);
		return true;
	}

	@Override
	@Transactional
	public boolean deleteById(Long themeId) {
		repository.deleteById(themeId);
		return true;
	}

	@Override
	public boolean deleteByParentId(Long parentId) {
		var themes = this.findAllByParentId(parentId);
		repository.deleteAll(themes);
		return true;
	}

	@Override
	public String getDescriptionById(Long themeId) {
		return this.getById(themeId).getDescription();
	}

	@Override
	public Theme getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BaseException(ExceptionCode.THEME_NOT_FOUND, id));
	}

	private List<Theme> findAllByParentId(Long parentId) {
		return repository.findAllByParentId(parentId);
	}
}
