package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmControllerTest {
    private FilmController controller = new FilmController();

    @Test
    public void shouldReturnErrorMessageIfFilmTooOld() throws IOException, InterruptedException {
        Film film = Film.builder()
                .name("name")
                .description("descr")
                .releaseDate(LocalDate.of(1895, 12, 27))
                .duration(120)
                .build();
        Exception exception = assertThrows(
                ValidationException.class,
                () -> controller.create(film)
        );
        assertEquals("Дата релиза фильма не может быть раньше 28 декабря 1895 года!", exception.getMessage());
    }
}
