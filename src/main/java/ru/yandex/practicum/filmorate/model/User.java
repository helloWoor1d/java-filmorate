package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Data
public class User {
    private Integer id;

    @NotBlank(message = "Email не может быть пустым!")
    @Email(message = "Введен некорректный email!")
    private final String email;

    @NotBlank(message = "Логин не может быть пустым!")
    private final String login;

    private final String name;

    @NotNull(message = "Укажите дату рождения!")
    @Past(message = "Дата рождения не может быть в будущем или настоящем!")
    private final LocalDate birthday;
}
