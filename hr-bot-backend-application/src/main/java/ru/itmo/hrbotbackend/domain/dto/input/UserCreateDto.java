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
public class UserCreateDto {
	private String tgId;
	private String fullName;
	private Long positionId;
	private Short roleId;
}
