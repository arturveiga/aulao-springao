package br.com.horys.demo.services;

import br.com.horys.demo.controllers.requests.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserService {
    public boolean validRequestToSave(UserRequest request) {
        if (request.password().length() < 2) {
            throw new RuntimeException("senha pequena");
        }

        return true;
    }

}
