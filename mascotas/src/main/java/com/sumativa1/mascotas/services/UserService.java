package com.sumativa1.mascotas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.dtos.UserDTO;
import com.sumativa1.mascotas.entities.Role;
import com.sumativa1.mascotas.entities.User;

@Service
public class UserService {
    private final RepositoryContract<User> userRepository;
    private final RepositoryContract<Role> roleRepository;

    public UserService(RepositoryContract<User> userRepository, RepositoryContract<Role> roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return userRepository.findAll().stream()
            .map(user -> {
                System.err.println("user role id" + user.getRoleId());
                System.out.println(roles.stream().filter(r -> r.getId().equals(user.getRoleId())).findFirst());
                Role role = roles.stream()
                    .filter(r -> r.getId().equals(user.getRoleId()))
                    .findFirst()
                    .orElse(null);
                System.out.println("Role: " + role);
                return new UserDTO(user.getId(), user.getName(), user.getRoleId(), role);
            })
            .collect(Collectors.toList());
    }

    public UserDTO findById(Number id) {
        User user = userRepository.findById(id);
        System.out.println("User: " + user);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Role role = roleRepository.findById(user.getRoleId());
        return new UserDTO(user.getId(), user.getName(), user.getRoleId(), role);
    }
}