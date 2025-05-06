package com.sumativa1.mascotas.repositories;

import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.entities.Role;
import com.sumativa1.mascotas.implementations.RoleRepositoryJpaImpl;
import com.sumativa1.mascotas.schemas.RoleSchema;

@Repository
@Primary
public class RoleRepositoryDbImpl implements RepositoryContract<Role> {

    private final RoleRepositoryJpaImpl jpa;

    public RoleRepositoryDbImpl(RoleRepositoryJpaImpl jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Role> findAll() {
        return jpa.findAll().stream()
                  .map(this::toEntity)
                  .toList();
    }

    @Override
    public Role findById(Long id) {
        return jpa.findById(id)
                  .map(this::toEntity)
                  .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    @Override
    public Role save(Role entity) {
        RoleSchema schema = toSchema(entity);
        return toEntity(jpa.save(schema));
    }

    private Role toEntity(RoleSchema schema) {
        return new Role(
            schema.getId(),
            schema.getName()
        );
    }

    public RoleSchema toSchema(Role entity) {
        RoleSchema schema = new RoleSchema();
        schema.setId(entity.getId());
        schema.setName(entity.getName());
        return schema;
    }
}
