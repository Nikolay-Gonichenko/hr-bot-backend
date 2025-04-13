package ru.itmo.hrbotbackend.domain.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.hrbotbackend.domain.dto.internal.InstructionTypeDto;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructionOutDto {

	private Long id;
	private byte[] content;
	private InstructionTypeDto instructionType;
}
