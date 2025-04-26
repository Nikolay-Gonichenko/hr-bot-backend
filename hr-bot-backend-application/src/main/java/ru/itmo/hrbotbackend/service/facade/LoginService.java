package ru.itmo.hrbotbackend.service.facade;

import ru.itmo.hrbotbackend.domain.dto.output.LoginDto;

/**
 *
 */
public interface LoginService {
	LoginDto login(String tgId);
}
