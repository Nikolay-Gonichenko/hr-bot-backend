package ru.itmo.hrbotbackend.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmo.hrbotbackend.domain.dto.input.UserBlockDto;
import ru.itmo.hrbotbackend.domain.dto.input.UserCreateDto;
import ru.itmo.hrbotbackend.domain.entity.User;

import java.util.Optional;

/**
 * 
 */
public interface UserService extends UserDetailsService {
	boolean createOrUpdateUser(UserCreateDto user);

	boolean blockUser(UserBlockDto userBlockDto);

	Optional<User> getByTgId(String authorTgId);
}
