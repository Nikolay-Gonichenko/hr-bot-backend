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
public class AnswerDto {

	private Long questionId;
	private String answer;
	private String responderId;
}
