package ru.itmo.hrbotbackend.service.instruction;

import ru.itmo.hrbotbackend.domain.dto.internal.InstructionCreateDto;
import ru.itmo.hrbotbackend.domain.dto.output.InstructionOutDto;

import java.util.List;

/**
 * 
 */
public interface InstructionService {

	List<InstructionOutDto> getByThemeId(Long themeId);

	boolean create(List<InstructionCreateDto> createDtoList);

	void deleteByThemeId(Long themeId);
}
