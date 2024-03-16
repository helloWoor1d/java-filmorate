package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {
    private UserController controller = new UserController();

    @Test
    public void shouldThrowExceptionIfLoginIsIncorrect() {
        User user = User.builder()
                .login("  lo gin")
                .name("name")
                .email("mail@mail.ru")
                .birthday(LocalDate.of(2004, 5, 13))
                .build();

        Exception exception = assertThrows(
                ValidationException.class,
                () -> controller.create(user)
        );
        assertEquals("Логин не может содержать пробелы!", exception.getMessage());
    }
}

