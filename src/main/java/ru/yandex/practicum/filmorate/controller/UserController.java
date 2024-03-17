package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.exceptions.DataNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController implements Controller<User> {

    private Map<Integer, User> users = new HashMap();
    private static int id = 1;

    @PostMapping
    public User create(@Valid @RequestBody  UserDTO userDTO) {
        User user = fromDTO(userDTO);
        validateUser(user);
        users.put(user.getId(), user);

        log.debug("Запрос на создание пользователя успешно обработан");
        return user;
    }

    @Override
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        validateUser(user);
        User savedUser = users.get(user.getId());

        if (savedUser != null) {
            users.put(user.getId(), user);
            log.debug("Запрос на изменение пользователя успешно обработан", user);
            return user;
        } else {
            throw new DataNotFoundException("Пользователь с таким id еще не был добавлен!");
        }
    }

    @Override
    @GetMapping
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    private User fromDTO(UserDTO userDTO) {
        if (userDTO.getName() == null) userDTO.setName(userDTO.getLogin());
        return User.builder()
                .id(generateId())
                .login(userDTO.getLogin())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .birthday(userDTO.getBirthday())
                .build();
    }

    private void validateUser(User user) {
        if (user.getLogin().contains(" ")) {
            throw new ValidationException("Логин не может содержать пробелы!");
        }
    }

    private int generateId() {
        return id++;
    }
}
