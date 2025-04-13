package ru.itmo.hrbotbackend.domain.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateThemeDto {

	private Long id;
	private String themeName;
	private String description;
	private Long parentId;
}
