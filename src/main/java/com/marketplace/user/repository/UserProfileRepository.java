package com.marketplace.user.repository;

import com.marketplace.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    boolean existsById(UUID id);
    boolean existsByCpf(String cpf);
}
