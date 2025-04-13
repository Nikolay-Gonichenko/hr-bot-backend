package ru.itmo.hrbotbackend.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.hrbotbackend.domain.entity.Role;
import ru.itmo.hrbotbackend.domain.repository.RoleRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;

/**
 * 
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository repository;
	@Override
	public Role getById(Short id) {
		return repository.findById(id)
				.orElseThrow(() -> new BaseException(ExceptionCode.ROLE_NOT_FOUND, id));
	}
}
