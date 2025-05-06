package com.sumativa1.mascotas.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.entities.User;

@Repository
public class UserRepositoryMemoryImpl implements RepositoryContract<User> {
    private final List<User> users = new ArrayList<>();

    public UserRepositoryMemoryImpl() {
        this.users.add(new User(1L, "Pedro Samuel", 1L));
        this.users.add(new User(2L, "Juan Carlos", 2L));
        this.users.add(new User(3L, "Luis Fernando", 1L));
        this.users.add(new User(4L, "María Fernanda", 2L));
    }

    public List<User> findAll() { 
        return users; 
    }

    public User findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User save(User entity) {
        users.add(entity);
        return entity;
    }
}