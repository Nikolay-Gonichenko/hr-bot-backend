package ru.itmo.hrbotbackend.service.position;

import org.mapstruct.Mapper;
import ru.itmo.hrbotbackend.domain.dto.common.PositionDto;
import ru.itmo.hrbotbackend.domain.entity.Position;

import java.util.List;

/**
 * 
 */
@Mapper(componentModel = "spring")
public interface PositionMapper {

	PositionDto mapToDto(Position position);

	List<PositionDto> mapListToDto(List<Position> positions);
}
