package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController implements Controller<Film> {
    private static int id = 1;
    private Map<Integer, Film> films = new HashMap<>();

    @PostMapping
    public Film create(@Valid @RequestBody FilmDTO filmDTO) {
        Film film = fromDTO(filmDTO);
        validateFilm(film);
        films.put(film.getId(), film);

        log.debug("Запрос на добавление фильма успешно обработан", film);
        return film;
    }

    @Override
    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        validateFilm(film);
        Film savedFilm = films.get(film.getId());
        if (savedFilm != null) {
            films.put(film.getId(), film);
            log.debug("Запрос на изменение фильма успешно обработан");
            return film;
        } else {
            throw new DataNotFoundException("Фильм с таким id еще не был добавлен!");
        }
    }

    @Override
    @GetMapping
    public List<Film> getAll() {
        return new ArrayList<>(films.values());
    }

    private Film fromDTO(FilmDTO filmDTO) {
        return Film.builder()
                .id(generateID())
                .name(filmDTO.getName())
                .description(filmDTO.getDescription())
                .releaseDate(filmDTO.getReleaseDate())
                .duration(filmDTO.getDuration())
                .build();
    }

    private int generateID() {
        return id++;
    }

    private void validateFilm(Film film) {
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата релиза фильма не может быть раньше 28 декабря 1895 года!");
        }
    }
}
