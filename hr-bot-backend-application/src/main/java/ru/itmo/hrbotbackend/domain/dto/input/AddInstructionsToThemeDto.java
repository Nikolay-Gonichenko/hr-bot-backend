package ru.itmo.hrbotbackend.domain.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddInstructionsToThemeDto {
	private String authorTgId;
	private Long themeId;
	private List<InputInstructionDto> instructions;
}
