package br.com.horys.demo.services;

import br.com.horys.demo.models.User;
import br.com.horys.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SearchUserService {

    private final UserRepository repository;

    public SearchUserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(UUID id) {
        final var user = repository.findById(id).orElseThrow(() -> new RuntimeException("nao achei esse cara"));
        return user;
    }
}
