package ru.itmo.hrbotbackend.service.position;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.hrbotbackend.domain.dto.common.PositionDto;
import ru.itmo.hrbotbackend.domain.entity.Position;
import ru.itmo.hrbotbackend.domain.repository.PositionRepository;
import ru.itmo.hrbotbackend.exception.BaseException;
import ru.itmo.hrbotbackend.exception.ExceptionCode;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

	private final PositionRepository repository;
	private final PositionMapper mapper;

	@Override
	@Transactional
	public boolean create(String name) {
		var entity = Position.builder().name(name).build();
		repository.save(entity);
		return true;
	}

	@Override
	public Position getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BaseException(ExceptionCode.POSITION_NOT_FOUND, id));
	}

	@Override
	public List<PositionDto> getAll() {
		return mapper.mapListToDto(repository.findAll());
	}

	@Override
	public boolean update(PositionDto positionDto) {
		var position = this.getById(positionDto.getId());
		position.setName(positionDto.getName());
		repository.save(position);
		return true;
	}

	@Override
	public boolean deleteById(Long id) {
		repository.deleteById(id);
		return true;
	}
}
