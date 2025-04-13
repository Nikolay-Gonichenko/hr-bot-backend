package ru.itmo.hrbotbackend.domain.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.hrbotbackend.domain.dto.internal.ThemeDto;

import java.util.List;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Content {
	private List<ThemeDto> children;
	private List<InstructionOutDto> instructions;
	private String description;
	private Boolean leaf;
}
