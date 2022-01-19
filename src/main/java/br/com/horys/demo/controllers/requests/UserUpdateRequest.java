package br.com.horys.demo.controllers.requests;

public record UserUpdateRequest(
    String name,
    String email,
    String password
) {
}
