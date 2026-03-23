package com.marketplace.user.listener;

import com.marketplace.user.domain.UserProfile;
import com.marketplace.user.dto.event.UserRegisteredEvent;
import com.marketplace.user.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedEventListener {

    private final UserProfileRepository repository;

    @RabbitListener(queues = "user.profile.create")
    @Transactional
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Evento recebido para criar perfil do usuário ID: {}", event.userId());

        if (repository.existsById(event.userId())) {
            log.warn("Perfil já existe para o usuário ID: {}", event.userId());
            return;
        }

        String provisionalName = event.email().split("@")[0];

        UserProfile profile = UserProfile.builder()
                .id(event.userId())
                .name(provisionalName)
                .build();

        repository.save(profile);
        log.info("Perfil base criado com sucesso para o usuário ID: {}", event.userId());
    }
}
