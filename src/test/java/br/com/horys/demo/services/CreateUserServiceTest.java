package br.com.horys.demo.services;

import br.com.horys.demo.controllers.requests.UserRequest;
import br.com.horys.demo.models.User;
import br.com.horys.demo.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith({MockitoExtension.class})
class CreateUserServiceTest {

    @Mock
    private ValidateUserService validateUserService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserService createUserService;


    @Test
    @DisplayName("Should be save a default user")
    void saveDefaultWithMockito() {
        UserRequest request = new UserRequest(
            "TESTE",
            "email@email",
            "pass1"
        );

        Mockito.when(userRepository.save(any()))
            .thenReturn(new User("Artur Mockito", "artur@gmail", "123", "default"));

        User user = createUserService.saveDefault(request);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getRole()).isEqualTo("default");
        Assertions.assertThat(user.getRole()).isNotEqualTo("admin");

    }

    @Test
    @DisplayName("When save a new user default expected a exception")
    void saveDefaulMockitoException() {
        UserRequest request = new UserRequest(
            "Teste",
            "email@email",
            "a"
        );

        Mockito.when(validateUserService.validRequestToSave(request)).thenThrow(new RuntimeException("Deu ruim"));

        Assertions.assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> createUserService.saveDefault(request))
        ;

    }

    @Test
    @DisplayName("Should be save a default user admin")
    void saveAdmin() {
        UserRequest request = new UserRequest(
            "TESTE",
            "email@email",
            "pass1"
        );

        Mockito.when(userRepository.save(any()))
            .thenReturn(new User("Artur Mockito", "artur@gmail", "123", "admin"));

        User user = createUserService.saveAdmin(request);

        Assertions.assertThat(user).isNotNull();

    }

    @Test
    @DisplayName("When save a new user admin expected a exception")
    void saveAdminException() {
        UserRequest request = new UserRequest(
            "Teste",
            "email@email",
            "a"
        );

        Mockito.when(validateUserService.validRequestToSave(request)).thenThrow(new RuntimeException("Deu ruim"));

        Assertions.assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> createUserService.saveAdmin(request))
        ;

    }
}