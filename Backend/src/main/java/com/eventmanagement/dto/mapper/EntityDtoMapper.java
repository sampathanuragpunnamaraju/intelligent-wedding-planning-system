package com.eventmanagement.dto.mapper;

public interface EntityDtoMapper<E, D> {

	D toDto(E entity);
}
