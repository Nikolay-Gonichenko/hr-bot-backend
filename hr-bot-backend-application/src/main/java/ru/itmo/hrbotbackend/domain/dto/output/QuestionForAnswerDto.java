package ru.itmo.hrbotbackend.domain.dto.output;

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
public class QuestionForAnswerDto {

	private Long id;
	private String question;
}
