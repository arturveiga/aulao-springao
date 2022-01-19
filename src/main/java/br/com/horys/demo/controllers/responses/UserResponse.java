package br.com.horys.demo.controllers.responses;

import java.util.UUID;

public record UserResponse(
    String name,
    String email,
    UUID id
) {
}
