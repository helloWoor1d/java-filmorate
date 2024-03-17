package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {
    private UserController controller = new UserController();

    @Test
    public void shouldThrowExceptionIfLoginIsIncorrect() {
        UserDTO userDTO = UserDTO.builder()
                .login("lo gin")
                .birthday(LocalDate.of(2004, 10, 4))
                .email("mail@mail.ru")
                .name("name")
                .build();

        Exception exception = assertThrows(
                ValidationException.class,
                () -> controller.create(userDTO)
        );
        assertEquals("Логин не может содержать пробелы!", exception.getMessage());
    }

    @Test
    public void shouldCreateUser() {
        UserDTO userDTO = UserDTO.builder()
                .login("login")
                .birthday(LocalDate.of(2000, 11, 2))
                .email("mail@mail.ru")
                .build();
        controller.create(userDTO);

        assertEquals(1, controller.getAll().size());
        User user = controller.getAll().get(0);

        assertEquals(userDTO.getLogin(), user.getLogin());
        assertEquals(userDTO.getLogin(), user.getName());
        assertEquals(userDTO.getBirthday(), user.getBirthday());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }
}

