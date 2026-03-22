package com.marketplace.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfilePublicResponse(
        UUID id,
        String name,
        Integer reputation,
        LocalDateTime createdAt
) {}
