package br.com.horys.demo.controllers;

import br.com.horys.demo.controllers.responses.UserResponse;
import br.com.horys.demo.services.SearchUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class SearchUserController {
    private final SearchUserService searchUserService;

    public SearchUserController(SearchUserService searchUserService) {
        this.searchUserService = searchUserService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return searchUserService.findAll().stream()
            .map(user -> new UserResponse(user.getName(), user.getEmail(), user.getId()))
            .toList();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable UUID id) {
        final var user = searchUserService.findById(id);

        return new UserResponse(
            user.getName(),
            user.getEmail(),
            user.getId()
        );
    }
}
