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

import java.util.List;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //-----------------------------------------------------------------------------
    // Létrehozás (createuser eljárás)

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {

        List<User> users = userRepository.getAllUsers();
        if (users.stream().anyMatch(u -> u.getUsername().equals(request.getUsername()))) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (users.stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        userRepository.createUser(
                null,                                              // image_id (még nincs kép)
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        User saved = userRepository.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("A felhasználó létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //-----------------------------------------------------------------------------


    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponse updateUser(Integer id, UpdateUserRequest request) {
        User user = findActiveUser(id);

        if (!user.getUsername().equals(request.getUsername())
                && userRepository.getAllUsers().stream()
                .anyMatch(u -> u.getUsername().equals(request.getUsername()))) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equals(request.getEmail()))) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        userRepository.updateUser(
                id,
                user.getImageId(),
                request.getUsername(),
                request.getEmail(),
                user.getIsActive()
        );

        return toResponse(findActiveUser(id));
    }

    @Transactional
    public void softDeleteUser(Integer id) {
        findActiveUser(id);
        userRepository.softDeleteUser(id);
    }

    public UserResponse getUserById(Integer id) {
        return toResponse(findActiveUser(id));
    }

    //-----------------------------------------------------------------------------
    // Segédmetódusok

    private User findActiveUser(Integer id) {
        return userRepository.getUserById(id)
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
}

//---------------------------------------------------------------------------------------------------------//