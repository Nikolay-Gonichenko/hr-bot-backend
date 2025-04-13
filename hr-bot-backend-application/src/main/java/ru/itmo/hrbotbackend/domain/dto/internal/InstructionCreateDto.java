package ru.itmo.hrbotbackend.domain.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.hrbotbackend.domain.entity.Theme;
import ru.itmo.hrbotbackend.domain.entity.User;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructionCreateDto {
	private byte[] content;
	private Theme theme;
	private User author;
	private Short typeId;
}
