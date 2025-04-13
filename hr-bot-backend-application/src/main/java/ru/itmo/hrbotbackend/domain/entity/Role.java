package ru.itmo.hrbotbackend.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Уровень доступа пользователя.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "id", nullable = false)
	private Short id;

	@Column(name = "name", nullable = false)
	private String name;
}
