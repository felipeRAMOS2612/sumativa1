package com.sumativa1.mascotas.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.entities.Role;

@Repository
public class RoleRepositoryMemoryImpl implements RepositoryContract<Role> {
    public final List<Role> roles = new ArrayList<Role>();

    public RoleRepositoryMemoryImpl() {
        roles.add(new Role(1L, "dueño de mascota"));
        roles.add(new Role(2L, "conductor"));
    }

    public List<Role> findAll() { 
        return roles; 
    }

    public Role findById(Long id) {
        return roles.stream().filter(role -> role.getId().equals(id)).findFirst().orElse(null);
    }
    
    public Role save(Role entity) {
        roles.add(entity);
        return entity;
    }
}