package ru.itmo.hrbotbackend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Коды исключений.
 */
@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

	ROLE_NOT_FOUND("D001", "Роль не найдена по ИД: %s"),
	POSITION_NOT_FOUND("D002", "Должность не найдена по ИД: %s"),
	USER_NOT_FOUND_BY_TG_ID("D003", "Пользователь не найден по ТГ ИД: %s"),
	THEME_NOT_FOUND("D004", "Тема не найдена по ИД: %s"),
	INSTRUCTION_TYPE_NOT_FOUND("D005", "Тип вложения не найден по ИД: %s"),

	USER_IS_ARCHIVED("U001", "Пользователь с ИД: %s заблокирован в системе"),

	THEME_IS_NOT_LEAF("T001", "Тема с названием \"%s\" не является листом. Создание" +
			" вложений невозможно.");

	private final String code;
	private final String message;
}
