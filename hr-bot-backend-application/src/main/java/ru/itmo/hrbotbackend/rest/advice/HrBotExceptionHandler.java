package ru.itmo.hrbotbackend.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.rest.advice.dto.ErrorRestResponse;

/**
 * Обработчик ошибок.
 */
@ResponseBody
@ControllerAdvice
public class HrBotExceptionHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ErrorRestResponse handleBusinessException(BaseException exception) {
		return ErrorRestResponse
				.builder()
				.code(exception.getExceptionCode().getCode())
				.message(exception.getMessage())
				.build();
	}
}
