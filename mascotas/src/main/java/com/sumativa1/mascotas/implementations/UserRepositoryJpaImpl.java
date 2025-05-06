package com.sumativa1.mascotas.implementations;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sumativa1.mascotas.schemas.UserSchema;

public interface UserRepositoryJpaImpl extends JpaRepository<UserSchema, Long> {

}