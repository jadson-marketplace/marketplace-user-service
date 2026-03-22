package com.marketplace.user.service;

import com.marketplace.user.domain.UserProfile;
import com.marketplace.user.dto.UserProfileRequest;
import com.marketplace.user.dto.UserProfileResponse;
import com.marketplace.user.exception.ProfileAlreadyExistsException;
import com.marketplace.user.exception.ProfileNotFoundException;
import com.marketplace.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository repository;

    @Transactional
    public UserProfileResponse createProfile(UUID userId, UserProfileRequest request) {
        if (repository.existsById(userId)) {
            throw new ProfileAlreadyExistsException("O perfil para este usuário já existe.");
        }

        if (request.cpf() != null && repository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("Este CPF já está em uso por outro perfil.");
        }

        UserProfile profile = UserProfile.builder()
                .id(userId)
                .name(request.name())
                .cpf(request.cpf())
                .phone(request.phone())
                .address(request.address())
                .build();

        UserProfile savedProfile = repository.save(profile);

        return new UserProfileResponse(
                savedProfile.getId(),
                savedProfile.getName(),
                savedProfile.getCpf(),
                savedProfile.getPhone(),
                savedProfile.getAddress(),
                savedProfile.getCreatedAt(),
                savedProfile.getUpdatedAt()
        );
    }

    @Transactional
    public UserProfileResponse updateProfile(UUID userId, UserProfileRequest request) {
        UserProfile profile = repository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException("Perfil não encontrado. Crie seu perfil primeiro."));

        profile.setName(request.name());
        profile.setPhone(request.phone());
        profile.setAddress(request.address());

        UserProfile updatedProfile = repository.save(profile);

        return new UserProfileResponse(
                updatedProfile.getId(),
                updatedProfile.getName(),
                updatedProfile.getCpf(),
                updatedProfile.getPhone(),
                updatedProfile.getAddress(),
                updatedProfile.getCreatedAt(),
                updatedProfile.getUpdatedAt()
        );
    }
}
