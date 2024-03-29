package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class Film {
    private Integer id;

    @NotBlank(message = "Название фильма не может быть пустым!")
    private String name;

    @Size(max = 200, message = "Длина описания фильма не должна превышать 200 символов!")
    private String description;

    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма не может быть отрицательной!")
    private int duration;
}
