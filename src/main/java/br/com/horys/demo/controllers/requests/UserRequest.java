package br.com.horys.demo.controllers.requests;

import javax.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank String name,
    @NotBlank String email,
    @NotBlank String password
) {}
