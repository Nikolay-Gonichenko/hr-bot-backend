package ru.itmo.hrbotbackend.service.instruction;

import org.mapstruct.Mapper;
import ru.itmo.hrbotbackend.domain.dto.internal.InstructionTypeDto;
import ru.itmo.hrbotbackend.domain.entity.InstructionType;

/**
 * 
 */
@Mapper(componentModel = "spring")
public interface InstructionTypeMapper {

	InstructionTypeDto mapToDto(InstructionType instructionType);
}
