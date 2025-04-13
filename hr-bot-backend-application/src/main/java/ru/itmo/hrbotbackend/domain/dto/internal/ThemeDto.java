package ru.itmo.hrbotbackend.domain.dto.internal;

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
public class ThemeDto {
	private Long id;
	private String themeName;
}
