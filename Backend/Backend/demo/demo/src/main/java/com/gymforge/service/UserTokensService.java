package com.gymforge.service;

import com.gymforge.dto.response.UserTokensResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.UserTokens;
import com.gymforge.repository.UserRepository;
import com.gymforge.repository.UserTokensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserTokensService {

    private final UserTokensRepository userTokensRepository;
    private final UserRepository userRepository;

    //--------------------------------------------------------------------------------//
    // Token létrehozása (createuser_tokens eljárás) - remember_token generálásával
    @Transactional
    public UserTokensResponse createRememberToken(Integer userId) {
        // Ellenőrizzük, hogy létezik-e ilyen felhasználó
        userRepository.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("A felhasználó nem található"));

        // 36 karakteres egyedi token generálása (befér a VARCHAR(100) mezőbe)
        String generatedToken = UUID.randomUUID().toString();

        userTokensRepository.createUserTokens(
                userId,
                generatedToken,
                null, // reset_password_token
                null, // reset_password_expires
                null  // email_verified_at
        );

        // Visszakeressük a frissen létrehozott sort (a legmagasabb id-jút ehhez a userhez)
        UserTokens saved = userTokensRepository.getAllUserTokens().stream()
                .filter(t -> t.getUserId().equals(userId) && generatedToken.equals(t.getRememberToken()))
                .max(Comparator.comparing(UserTokens::getId))
                .orElseThrow(() -> new ResourceNotFoundException("A token létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //--------------------------------------------------------------------------------//
    // Összes token lekérése (getuser_tokens eljárás)
    public List<UserTokensResponse> getAllUserTokens() {
        return userTokensRepository.getAllUserTokens().stream()
                .map(this::toResponse)
                .toList();
    }

    //--------------------------------------------------------------------------------//
    // Egy token lekérése id alapján (getByIduser_tokens eljárás)
    public UserTokensResponse getUserTokensById(Integer id) {
        return toResponse(findUserTokens(id));
    }

    //--------------------------------------------------------------------------------//
    // Token frissítése (updateuser_tokens eljárás)
    @Transactional
    public UserTokensResponse updateRememberToken(Integer id) {
        UserTokens existing = findUserTokens(id);
        String newToken = UUID.randomUUID().toString();

        userTokensRepository.updateUserTokens(
                id,
                existing.getUserId(),
                newToken,
                existing.getResetPasswordToken(),
                existing.getResetPasswordExpires(),
                existing.getEmailVerifiedAt()
        );

        return toResponse(findUserTokens(id));
    }

    //--------------------------------------------------------------------------------//
    // Segédmetódusok
    private UserTokens findUserTokens(Integer id) {
        return userTokensRepository.getUserTokensById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A token nem található"));
    }

    private UserTokensResponse toResponse(UserTokens token) {
        return UserTokensResponse.builder()
                .id(token.getId())
                .userId(token.getUserId())
                .rememberToken(token.getRememberToken())
                .resetPasswordToken(token.getResetPasswordToken())
                .resetPasswordExpires(token.getResetPasswordExpires())
                .emailVerifiedAt(token.getEmailVerifiedAt())
                .createdAt(token.getCreatedAt())
                .updatedAt(token.getUpdatedAt())
                .build();
    }
}