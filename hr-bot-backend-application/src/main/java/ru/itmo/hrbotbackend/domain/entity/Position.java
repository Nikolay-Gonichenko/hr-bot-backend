package ru.itmo.hrbotbackend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Должность сотрудника.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class Position {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positionSeq")
	@SequenceGenerator(sequenceName = "position_seq", name = "positionSeq", allocationSize = 1)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
}
