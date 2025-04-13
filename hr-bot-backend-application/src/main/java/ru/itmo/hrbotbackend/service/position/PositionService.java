package ru.itmo.hrbotbackend.service.position;

import ru.itmo.hrbotbackend.domain.dto.common.PositionDto;
import ru.itmo.hrbotbackend.domain.entity.Position;

import java.util.List;

/**
 *
 */
public interface PositionService {

	boolean create(String name);

	Position getById(Long id);

	List<PositionDto> getAll();

	boolean update(PositionDto positionDto);

	boolean deleteById(Long id);
}
