package com.marketplace.user.dto.event;

import java.util.UUID;

public record UserRegisteredEvent(
        UUID userId,
        String email
) {}
