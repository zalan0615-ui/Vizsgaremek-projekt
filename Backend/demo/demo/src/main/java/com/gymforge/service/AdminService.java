package com.gymforge.service;

import com.gymforge.dto.request.CreateAdminRequest;
import com.gymforge.dto.request.UpdateAdminRequest;
import com.gymforge.dto.response.AdminResponse;
import com.gymforge.exception.DuplicateResourceException;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Admin;
import com.gymforge.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//---------------------------------------------------------------------------------------------------------//
//---------------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    //-----------------------------------------------------------------------------
    // Létrehozás (createadmin eljárás)

    @Transactional
    public AdminResponse createAdmin(CreateAdminRequest request) {

        // Foglalt-e már (a getadmin eljárás listájából nézzük, mert nincs külön eljárás hozzá)
        List<Admin> admins = adminRepository.getAllAdmins();
        if (admins.stream().anyMatch(a -> a.getUsername().equals(request.getUsername()))) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (admins.stream().anyMatch(a -> a.getEmail().equals(request.getEmail()))) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        // A jelszót hash-eljük, az eljárás a password_hash-t várja
        adminRepository.createAdmin(
                null,                                              // image_id (még nincs kép)
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        // Az eljárás nem ad vissza sort, ezért az emailből keressük vissza az új admint
        Admin saved = adminRepository.getAllAdmins().stream()
                .filter(a -> a.getEmail().equals(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Az admin létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //-----------------------------------------------------------------------------
    // Lista, módosítás, soft delete

    public List<AdminResponse> getAllAdmins() {
        return adminRepository.getAllAdmins()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public AdminResponse updateAdmin(Integer id, UpdateAdminRequest request) {
        Admin admin = findActiveAdmin(id);

        if (!admin.getUsername().equals(request.getUsername())
                && adminRepository.getAllAdmins().stream()
                .anyMatch(a -> a.getUsername().equals(request.getUsername()))) {
            throw new DuplicateResourceException("Ez a felhasználónév már foglalt");
        }
        if (!admin.getEmail().equals(request.getEmail())
                && adminRepository.getAllAdmins().stream()
                .anyMatch(a -> a.getEmail().equals(request.getEmail()))) {
            throw new DuplicateResourceException("Ez az email már regisztrálva van");
        }

        // Az eljárás mind az 5 paramétert várja, a kép és az aktív állapot marad a régi
        adminRepository.updateAdmin(
                id,
                admin.getImageId(),
                request.getUsername(),
                request.getEmail(),
                admin.getIsActive()
        );

        return toResponse(findActiveAdmin(id)); // újra beolvassuk a módosított sort
    }

    @Transactional
    public void softDeleteAdmin(Integer id) {
        findActiveAdmin(id);                   // ha nincs ilyen admin, 404
        adminRepository.softDeleteAdmin(id);   // softDeleteadmin eljárás
    }

    public AdminResponse getAdminById(Integer id) {
        return toResponse(findActiveAdmin(id));
    }

    //-----------------------------------------------------------------------------
    // Segédmetódusok

    private Admin findActiveAdmin(Integer id) {
        return adminRepository.getAdminById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Az admin nem található"));
    }

    private AdminResponse toResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .isActive(admin.getIsActive())
                .build();
    }
}

//---------------------------------------------------------------------------------------------------------//