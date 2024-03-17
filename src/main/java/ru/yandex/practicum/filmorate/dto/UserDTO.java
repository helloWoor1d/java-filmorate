package ru.yandex.practicum.filmorate.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserDTO {
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
