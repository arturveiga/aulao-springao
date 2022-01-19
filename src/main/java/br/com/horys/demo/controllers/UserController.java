package br.com.horys.demo.controllers;

import br.com.horys.demo.controllers.requests.UserRequest;
import br.com.horys.demo.controllers.requests.UserUpdateRequest;
import br.com.horys.demo.controllers.responses.UserResponse;
import br.com.horys.demo.services.CreateUserService;
import br.com.horys.demo.services.UpdateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    public UserController(CreateUserService createUserService,
                          UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@Valid @RequestBody UserRequest request) {
        final var user = createUserService.saveDefault(request);

        return new UserResponse(
            user.getName(),
            user.getEmail(),
            user.getId()
        );
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveAdmin(@Valid @RequestBody UserRequest request) {
        final var user = createUserService.saveAdmin(request);

        return new UserResponse(
            user.getName(),
            user.getEmail(),
            user.getId()
        );
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable UUID id, @RequestBody UserUpdateRequest request) {
        final var user = updateUserService.updateNameAndEmail(id, request);
        return new UserResponse(
            user.getName(),
            user.getEmail(),
            user.getId()
        );
    }

}
