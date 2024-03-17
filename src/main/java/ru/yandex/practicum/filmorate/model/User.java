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
    private String email;

    @NotBlank(message = "Логин не может быть пустым!")
    private String login;

    private String name;

    @NotNull(message = "Укажите дату рождения!")
    @Past(message = "Дата рождения не может быть в будущем или настоящем!")
    private LocalDate birthday;
}
