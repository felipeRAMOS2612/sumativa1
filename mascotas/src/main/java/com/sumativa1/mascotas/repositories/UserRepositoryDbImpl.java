package com.sumativa1.mascotas.repositories;

import java.util.List;
import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.entities.Role;
import com.sumativa1.mascotas.entities.User;
import com.sumativa1.mascotas.implementations.UserRepositoryJpaImpl;
import com.sumativa1.mascotas.schemas.RoleSchema;
import com.sumativa1.mascotas.schemas.UserSchema;

public class UserRepositoryDbImpl implements RepositoryContract<User> {

    private final UserRepositoryJpaImpl jpa;
    private final RoleRepositoryDbImpl roleRepository;

    public UserRepositoryDbImpl(UserRepositoryJpaImpl jpa, RoleRepositoryDbImpl roleRepository) {
        this.jpa = jpa;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return jpa.findAll().stream()
                  .map(this::toEntity)
                  .toList();
    }

    @Override
    public User findById(Long id) {
        return jpa.findById(id)
                  .map(this::toEntity)
                  .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User save(User entity) {
        UserSchema schema = toSchema(entity);
        return toEntity(jpa.save(schema));
    }

    private User toEntity(UserSchema schema) {
        return new User(
            schema.getId(),
            schema.getName(),
            schema.getRole().getId()
        );
    }

    private UserSchema toSchema(User entity) {
        UserSchema schema = new UserSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        
        Role role = roleRepository.findById(entity.getRoleId());
        RoleSchema roleSchema = roleRepository.toSchema(role);
        schema.setRole(roleSchema);
        
        return schema;
    }
}
