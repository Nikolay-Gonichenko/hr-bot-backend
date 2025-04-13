package ru.itmo.hrbotbackend.exception;

import lombok.Getter;

/**
 * Базоввое исключение.
 */
@Getter
public class BaseException extends RuntimeException {

	private final ExceptionCode exceptionCode;

	public BaseException(ExceptionCode exceptionCode, Object... params) {
		super(String.format(exceptionCode.getMessage(), params));
		this.exceptionCode = exceptionCode;
	}


}
