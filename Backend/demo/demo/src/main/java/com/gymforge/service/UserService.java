package com.gymforge.service;

import com.gymforge.dto.request.CreateUserRequest;
import com.gymforge.dto.request.UpdateUserRequest;
import com.gymforge.dto.response.UserResponse;
import com.gymforge.exception.DuplicateResourceException;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.User;
import com.gymforge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        User saved = userRepository.save(user);

        return toResponse(saved);
    }

    //----------------------------------------------------------------------------
    // Lista, módosítás, soft delete

    public List<UserResponse> getAllUsers() {
        return userRepository.findAllActive()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse updateUser(Integer id, UpdateUserRequest request) {
        User user = findActiveUser(id);

        if (!user.getUsername().equals(request.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void softDeleteUser(Integer id) {
        User user = findActiveUser(id);
        user.setIsDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    private User findActiveUser(Integer id) {
        return userRepository.findActiveById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A felhasználó nem található"));
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .build();
    }

    public UserResponse getUserById(Integer id) {
        return toResponse(findActiveUser(id));
    }
}