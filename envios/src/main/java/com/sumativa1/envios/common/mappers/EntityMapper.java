package com.sumativa1.envios.common.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityMapper<D, E> {

    public abstract E toEntity(D dto);
    public abstract D toDto(E entity);

    public List<E> toEntityList(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<D> toDtoList(List<E> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
