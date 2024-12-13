package com.epam.jmp.rest.service;

import com.epam.jmp.rest.exception.UserNotFoundException;
import com.epam.jmp.rest.mapper.UserMapper;
import com.epam.jmp.rest.model.User;
import com.epam.jmp.rest.model.UserDTO;
import com.epam.jmp.rest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserDTO> dtos = users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, users.getTotalElements());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found", String.format("User with id %s is not found", id)));
        return userMapper.convertToDto(user);
    }

    public UserDTO create(UserDTO user) {
        User entity = userMapper.convertToEntity(user);
        User saved = userRepository.save(entity);
        return userMapper.convertToDto(saved);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
