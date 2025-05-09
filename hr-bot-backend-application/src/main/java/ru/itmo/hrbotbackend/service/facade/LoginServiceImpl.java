package ru.itmo.hrbotbackend.service.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hrbotbackend.domain.dto.output.LoginDto;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;
import ru.itmo.hrbotbackend.security.util.JwtTokenUtil;
import ru.itmo.hrbotbackend.service.user.UserService;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final JwtTokenUtil jwtTokenUtil;
	private final UserService userService;

	@Override
	public LoginDto login(String tgId) {
		var user = userService.getByTgId(tgId)
				.orElseThrow(() -> new BaseException(ExceptionCode.USER_NOT_FOUND_BY_TG_ID, tgId));
		return new LoginDto(user.getRole().getName(), jwtTokenUtil.generateAccessToken(tgId));
	}
}
