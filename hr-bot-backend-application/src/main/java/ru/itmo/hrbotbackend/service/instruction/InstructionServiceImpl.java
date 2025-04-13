package ru.itmo.hrbotbackend.service.instruction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.internal.InstructionCreateDto;
import ru.itmo.hrbotbackend.domain.dto.output.InstructionOutDto;
import ru.itmo.hrbotbackend.domain.entity.Instruction;
import ru.itmo.hrbotbackend.domain.entity.InstructionType;
import ru.itmo.hrbotbackend.domain.repository.InstructionRepository;
import ru.itmo.hrbotbackend.domain.repository.InstructionTypeRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class InstructionServiceImpl implements InstructionService {

	private final InstructionRepository repository;
	private final InstructionTypeMapper instructionTypeMapper;
	private final InstructionTypeRepository instructionTypeRepository;

	@Override
	public List<InstructionOutDto> getByThemeId(Long themeId) {
		return repository.findAllByThemeId(themeId)
				.stream()
				.map(instruction -> InstructionOutDto
						.builder()
						.id(instruction.getId())
						.content(instruction.getContent())
						.instructionType(instructionTypeMapper.mapToDto(instruction.getInstructionType()))
						.build()
				)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public boolean create(List<InstructionCreateDto> createDtoList) {
		var entities = createDtoList
				.stream()
				.map(createDto -> Instruction
						.builder()
						.content(createDto.getContent())
						.author(createDto.getAuthor())
						.theme(createDto.getTheme())
						.instructionType(this.getTypeById(createDto.getTypeId()))
						.build())
				.collect(Collectors.toList());

		repository.saveAll(entities);
		return true;
	}

	@Override
	@Transactional
	public void deleteByThemeId(Long themeId) {
		repository.deleteAllByThemeId(themeId);
	}

	private InstructionType getTypeById(Short id) {
		return instructionTypeRepository.findById(id)
				.orElseThrow(() -> new BaseException(ExceptionCode.INSTRUCTION_TYPE_NOT_FOUND, id));
	}
}
