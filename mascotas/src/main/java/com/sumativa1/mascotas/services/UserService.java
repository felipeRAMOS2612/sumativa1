package com.sumativa1.mascotas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sumativa1.mascotas.contracts.RepositoryContract;
import com.sumativa1.mascotas.dtos.UserDto;
import com.sumativa1.mascotas.dtos.UserRequest;
import com.sumativa1.mascotas.entities.Role;
import com.sumativa1.mascotas.entities.User;
import com.sumativa1.mascotas.mappers.UserMapper;

@Service
public class UserService {
    private final RepositoryContract<User> userRepository;
    private final RepositoryContract<Role> roleRepository;
    private final UserMapper userMapper;

    public UserService(RepositoryContract<User> userRepository, RepositoryContract<Role> roleRepository, UserMapper userMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return userRepository.findAll().stream()
            .map(user -> {
                Role role = roles.stream()
                    .filter(r -> r.getId().equals(user.getRoleId()))
                    .findFirst()
                    .orElse(null);
                return new UserDto(user.getId(), user.getName(), user.getRoleId(), role);
            })
            .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        System.out.println("User: " + user);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Role role = roleRepository.findById(user.getRoleId());
        return new UserDto(user.getId(), user.getName(), user.getRoleId(), role);
    }

    public User save(UserRequest userDto) {
        User userEntity = userMapper.toEntity(userDto);
        return userRepository.save(userEntity);
    }
}