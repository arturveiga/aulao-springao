package br.com.horys.demo.services;

import br.com.horys.demo.controllers.requests.UserRequest;
import br.com.horys.demo.models.User;
import br.com.horys.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    private final UserRepository repository;
    private final ValidateUserService validateUserService;

    public CreateUserService(UserRepository repository,
                             ValidateUserService validateUserService) {
        this.repository = repository;
        this.validateUserService = validateUserService;
    }

    public User saveDefault(UserRequest request) {
        validateUserService.validRequestToSave(request);

        final var user = new User(
            request.name(),
            request.email(),
            request.password(),
            "default"
        );

        return repository.save(user);
    }

    public User saveAdmin(UserRequest request) {
        validateUserService.validRequestToSave(request);

        final var user = new User(
            request.name(),
            request.email(),
            request.password(),
            "admin"
        );

        return repository.save(user);
    }
}
