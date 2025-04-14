package ru.itmo.hrbotbackend.rest.advice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответ ошибки на рест запрос.
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRestResponse {

	@NotNull
	private String code;
	@NotNull
	private String message;
}
