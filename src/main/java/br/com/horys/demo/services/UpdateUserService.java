package br.com.horys.demo.services;

import br.com.horys.demo.controllers.requests.UserUpdateRequest;
import br.com.horys.demo.models.User;
import br.com.horys.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UpdateUserService {
    private final UserRepository repository;

    public UpdateUserService(UserRepository repository) {
        this.repository = repository;
    }

    public User updateNameAndEmail(UUID id, UserUpdateRequest request) {
        final var user = repository.findById(id).orElseThrow(() -> new RuntimeException("nao achei esse cara"));

        if (StringUtils.hasText(request.name())) {
            user.setName(request.name());
        }

        if (StringUtils.hasText(request.email())) {
            user.setEmail(request.email());
        }

        return repository.save(user);
    }

    public User updatePassword(UUID id, UserUpdateRequest request) {
        final var user = repository.findById(id).orElseThrow(() -> new RuntimeException("nao achei esse cara"));

        if (StringUtils.hasText(request.password()) && request.password().length() > 3) {
            // TODO: FAZER ALGO AQUI
        }

        return repository.save(user);
    }
}
