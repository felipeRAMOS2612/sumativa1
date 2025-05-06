package com.sumativa1.mascotas.implementations;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.mascotas.schemas.RoleSchema;

public interface RoleRepositoryJpaImpl extends JpaRepository<RoleSchema, Long> {

}