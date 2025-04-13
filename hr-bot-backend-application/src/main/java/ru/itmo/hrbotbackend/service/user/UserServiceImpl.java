package ru.itmo.hrbotbackend.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.input.UserBlockDto;
import ru.itmo.hrbotbackend.domain.dto.input.UserCreateDto;
import ru.itmo.hrbotbackend.domain.entity.User;
import ru.itmo.hrbotbackend.domain.repository.UserRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;
import ru.itmo.hrbotbackend.service.position.PositionService;
import ru.itmo.hrbotbackend.service.role.RoleService;

import java.util.Optional;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PositionService positionService;
	private final RoleService roleService;

	@Override
	public Optional<User> getByTgId(String tgId) {
		return userRepository.findByTgId(tgId);
	}

	@Override
	@Transactional
	public boolean createOrUpdateUser(UserCreateDto user) {
		var tgId = user.getTgId();
		var fullName = user.getFullName();

		var role = roleService.getById(user.getRoleId());
		var position = positionService.getById(user.getPositionId());
		var optional = this.getByTgId(tgId);
		User entity;
		if (optional.isEmpty()) {
			entity = User
					.builder()
					.tgId(user.getTgId())
					.role(role)
					.position(position)
					.fullName(fullName)
					.archived(false)
					.build();
		} else {
			entity = optional.get();
			if (entity.getArchived()) {
				throw new BaseException(ExceptionCode.USER_IS_ARCHIVED, tgId);
			}
			entity.setRole(role);
			entity.setFullName(fullName);
			entity.setPosition(position);
		}

		userRepository.save(entity);
		return true;
	}

	@Override
	public boolean blockUser(UserBlockDto userBlockDto) {
		var tgId = userBlockDto.getTgId();
		var user = this.getByTgId(tgId)
				.orElseThrow(() -> new BaseException(ExceptionCode.USER_NOT_FOUND_BY_TG_ID, tgId));
		user.setArchived(userBlockDto.getArchived());
		userRepository.save(user);
		return true;
	}
}
