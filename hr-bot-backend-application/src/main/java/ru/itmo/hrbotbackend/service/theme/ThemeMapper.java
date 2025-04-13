package ru.itmo.hrbotbackend.service.theme;

import org.mapstruct.Mapper;
import ru.itmo.hrbotbackend.domain.dto.internal.ThemeDto;
import ru.itmo.hrbotbackend.domain.entity.Theme;

import java.util.List;

/**
 * 
 */
@Mapper(componentModel = "spring")
public interface ThemeMapper {

	List<ThemeDto> mapListToDto(List<Theme> themes);
}
