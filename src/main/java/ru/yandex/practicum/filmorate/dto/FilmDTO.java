package ru.yandex.practicum.filmorate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FilmDTO {
    @NotBlank(message = "Название фильма не может быть пустым!")
    private String name;

    @Size(max = 200, message = "Длина описания фильма не должна превышать 200 символов!")
    private String description;

    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма не может быть отрицательной!")
    private int duration;
}
