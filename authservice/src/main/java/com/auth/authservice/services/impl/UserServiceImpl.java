package com.auth.authservice.services.impl;

import com.auth.authservice.dtos.UserDTO;
import com.auth.authservice.entities.Provider;
import com.auth.authservice.entities.User;
import com.auth.authservice.exceptions.ResourceNotFoundException;
import com.auth.authservice.repositories.UserRepository;
import com.auth.authservice.services.UserService;
import com.auth.authservice.utilities.UserUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("User with email " + userDTO.getEmail() + " already exists");
        }
        User userEntity = modelMapper.map(userDTO, User.class);

        userEntity.setProvider(userDTO.getProvider() != null ? userDTO.getProvider() : Provider.LOCAL);

        User savedUser = userRepository.save(userEntity);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmailId(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String userId) {
        UUID uid = UserUtility.parseUUID(userId);
        User existingUser = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }
        if (userDTO.getImage() != null) {
            existingUser.setImage(userDTO.getImage());
        }
        if (userDTO.getProvider() != null) {
            existingUser.setProvider(userDTO.getProvider());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }
        existingUser.setEnable(userDTO.isEnable());
        existingUser.setUpdatedAt(Instant.now());

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID id = UserUtility.parseUUID(userId);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDTO getUserById(String userId) {
        UUID id = UserUtility.parseUUID(userId);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public Iterable<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream().map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
