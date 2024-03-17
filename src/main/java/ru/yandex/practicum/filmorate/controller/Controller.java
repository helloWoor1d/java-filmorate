package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface Controller<T> {

    @PutMapping
    T update(@RequestBody T obj);

    @GetMapping
    List<T> getAll();
}
